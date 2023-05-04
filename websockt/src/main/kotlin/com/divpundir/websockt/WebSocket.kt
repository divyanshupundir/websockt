package com.divpundir.websockt

public interface WebSocket {

    public fun send(payload: String)

    public fun close(code: Int = 1000, reason: String? = null)

    public sealed interface Event {

        public object Open : Event

        public data class Message(val payload: String) : Event

        public data class Closing(val code: Int, val reason: String?) : Event

        public data class Close(val code: Int, val reason: String?) : Event

        public fun interface Listener {

            public fun onEvent(event: Event)
        }
    }

    public fun interface FailureListener {

        public fun onFailure(t: Throwable)
    }
}
