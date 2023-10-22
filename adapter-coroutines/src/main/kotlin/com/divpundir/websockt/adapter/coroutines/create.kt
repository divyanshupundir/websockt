package com.divpundir.websockt.adapter.coroutines

import com.divpundir.websockt.WebSocktFactory
import kotlinx.coroutines.CoroutineScope

public fun CoroutinesWebSocktClient(
    factory: WebSocktFactory,
    scope: CoroutineScope
): CoroutinesWebSocktClient = CoroutinesWebSocktClientImpl(
    factory,
    scope
)
