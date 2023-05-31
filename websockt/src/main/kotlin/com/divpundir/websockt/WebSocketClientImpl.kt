package com.divpundir.websockt

internal class WebSocketClientImpl(
    private val factory: WebSocketFactory,
    private val onFailure: WebSocketClient.FailureListener,
    private val onEvent: WebSocketClient.Event.Listener
) : WebSocketClient {

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
            onFailure = { onFailure.onFailure(this, it) },
            onEvent = {
                when (it) {
                    is WebSocket.Event.Open -> onEvent.onEvent(WebSocketClient.Event.Open)
                    is WebSocket.Event.Message -> onEvent.onEvent(WebSocketClient.Event.Message(it.payload))
                    is WebSocket.Event.Closing -> onEvent.onEvent(WebSocketClient.Event.Closing(it.code, it.reason))
                    is WebSocket.Event.Close -> onEvent.onEvent(WebSocketClient.Event.Close(it.code, it.reason))
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

    override fun close(code: Int, reason: String?) {
        val s = state
        if (s is State.Active) {
            s.socket.close(code, reason)
            state = State.Inactive
        }
    }

    private sealed interface State {

        class Active(val socket: WebSocket) : State

        object Inactive : State
    }
}
