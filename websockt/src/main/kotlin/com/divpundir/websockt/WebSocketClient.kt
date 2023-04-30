package com.divpundir.websockt

public class WebSocketClient(
    private val factory: WebSocketFactory,
    private val onFailure: FailureListener,
    private val onEvent: Event.Listener
) {
    @Volatile
    private var state: State = State.Inactive
        @Synchronized get
        @Synchronized set

    public fun open(url: String) {
        val s = state
        if (s is State.Active) {
            s.socket.close()
        }

        val socket = factory.create(
            url,
            onFailure = { onFailure.onFailure(this, it) },
            onEvent = {
                when (it) {
                    is WebSocket.Event.Open -> onEvent.onEvent(Event.Open)
                    is WebSocket.Event.Message -> onEvent.onEvent(Event.Message(it.payload))
                    is WebSocket.Event.Closing -> onEvent.onEvent(Event.Closing(it.code, it.reason))
                    is WebSocket.Event.Close -> onEvent.onEvent(Event.Close(it.code, it.reason))
                }
            }
        )

        state = State.Active(socket)
    }

    public fun send(payload: String) {
        val s = state
        if (s is State.Active) {
            s.socket.send(payload)
        }
    }

    public fun close(code: Int = 1000, reason: String? = null) {
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

    public sealed interface Event {

        public object Open : Event

        public data class Message(val payload: String) : Event

        public data class Closing(val code: Int, val reason: String) : Event

        public data class Close(val code: Int, val reason: String) : Event

        public fun interface Listener {

            public fun onEvent(event: Event)
        }
    }

    public fun interface FailureListener {

        public fun onFailure(webSocketClient: WebSocketClient, t: Throwable)
    }
}
