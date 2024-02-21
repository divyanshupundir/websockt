package com.divpundir.websockt.adapter.coroutines

import com.divpundir.websockt.WebSocktFactory

public fun CoroutinesWebSocktClient(
    factory: WebSocktFactory,
): CoroutinesWebSocktClient = CoroutinesWebSocktClientImpl(
    factory,
)
