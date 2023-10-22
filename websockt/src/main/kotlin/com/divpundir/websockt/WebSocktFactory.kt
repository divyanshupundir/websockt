package com.divpundir.websockt

public interface WebSocktFactory {

    public fun create(
        url: String,
        onEvent: WebSockt.Event.Listener
    ): WebSockt
}
