package com.divpundir.websockt

public sealed interface WebSocketEvent {

    public object Open : WebSocketEvent

    public data class Message(val payload: String) : WebSocketEvent

    public data class Closing(val code: Int, val reason: String) : WebSocketEvent

    public data class Close(val code: Int, val reason: String) : WebSocketEvent

    public data class Failure(val t: Throwable) : WebSocketEvent

    public fun interface Listener {

        public fun onEvent(event: WebSocketEvent)
    }
}
