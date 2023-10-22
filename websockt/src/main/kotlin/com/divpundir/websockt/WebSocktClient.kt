package com.divpundir.websockt

public interface WebSocktClient {

    public fun open(url: String)

    public fun send(payload: String)

    public fun close(code: Int = 1000, reason: String? = null)

    public sealed interface Event {

        public data object Open : Event

        public data class Closing(val code: Int, val reason: String?) : Event

        public data class Close(val code: Int, val reason: String?) : Event

        public data class Failure(val cause: Throwable) : Event

        public sealed interface Message : Event {

            public data class Text(val payload: String) : Message

            public data class Bytes(val payload: ByteArray) : Message {

                override fun equals(other: Any?): Boolean {
                    if (this === other) return true
                    if (javaClass != other?.javaClass) return false

                    other as Bytes

                    return payload.contentEquals(other.payload)
                }

                override fun hashCode(): Int {
                    return payload.contentHashCode()
                }
            }
        }

        public fun interface Listener {

            public fun onEvent(event: Event)
        }
    }
}
