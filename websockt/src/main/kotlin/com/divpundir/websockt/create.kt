package com.divpundir.websockt

public fun WebSocktClient(
    factory: WebSocktFactory,
    onEvent: WebSocktClient.Event.Listener
): WebSocktClient = WebSocktClientImpl(
    factory = factory,
    onEvent = onEvent
)
