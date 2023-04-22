package com.divpundir.websockt

public interface WebSocketClient {

    public val state: State

    public fun open(url: String)

    public fun send(payload: String)

    public fun close(code: Int = 1000, reason: String? = null)

    public sealed interface State {

        public object Active : State

        public object Inactive : State
    }
}
