package com.divpundir.websockt.api

public interface WebSocket {

    public val url: String

    public fun send(payload: String)

    public fun close(code: Int = 1000, reason: String? = null)

    public interface Event {

        public object Open : Event

        public data class Message(val payload: String) : Event

        public data class Closing(val code: Int, val reason: String) : Event

        public data class Close(val code: Int, val reason: String) : Event

        public data class Failure(val t: Throwable) : Event
    }
}
