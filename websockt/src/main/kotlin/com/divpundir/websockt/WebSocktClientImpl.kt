package com.divpundir.websockt

internal class WebSocktClientImpl(
    private val factory: WebSocktFactory,
    private val onEvent: WebSocktClient.Event.Listener,
) : WebSocktClient {

    @Volatile
    private var state: State = State.Inactive
        @Synchronized get
        @Synchronized set

    override fun open(url: String) {
        val s = state
        if (s is State.Active) {
            s.socket.close()
        }

        val socket = factory.create(
            url,
            onEvent = {
                when (it) {
                    is WebSockt.Event.Open -> onEvent.onEvent(WebSocktClient.Event.Open)
                    is WebSockt.Event.Closing -> onEvent.onEvent(WebSocktClient.Event.Closing(it.code, it.reason))
                    is WebSockt.Event.Close -> onEvent.onEvent(WebSocktClient.Event.Close(it.code, it.reason))
                    is WebSockt.Event.Failure -> onEvent.onEvent(WebSocktClient.Event.Failure(it.cause))
                    is WebSockt.Event.Message -> {
                        when (it) {
                            is WebSockt.Event.Message.Text -> onEvent.onEvent(WebSocktClient.Event.Message.Text(it.payload))
                            is WebSockt.Event.Message.Bytes -> onEvent.onEvent(WebSocktClient.Event.Message.Bytes(it.payload))
                        }
                    }
                }
            }
        )

        state = State.Active(socket)
    }

    override fun send(payload: String) {
        val s = state
        if (s is State.Active) {
            s.socket.send(payload)
        }
    }

    override fun send(payload: ByteArray) {
        val s = state
        if (s is State.Active) {
            s.socket.send(payload)
        }
    }

    override fun close(code: Int, reason: String?) {
        val s = state
        if (s is State.Active) {
            s.socket.close(code, reason)
            state = State.Inactive
        }
    }

    private sealed interface State {

        class Active(val socket: WebSockt) : State

        data object Inactive : State
    }
}
