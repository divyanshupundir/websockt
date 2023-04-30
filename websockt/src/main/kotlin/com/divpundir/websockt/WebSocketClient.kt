package com.divpundir.websockt

public class WebSocketClient(
    private val factory: WebSocketFactory,
    private val listener: WebSocketEvent.Listener
) {
    @Volatile private var state: State = State.Inactive
        @Synchronized get
        @Synchronized set

    public fun open(url: String) {
        val s = state
        if (s is State.Active) {
            s.socket.close()
        }

        val socket = factory.create(url, listener)
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
}
