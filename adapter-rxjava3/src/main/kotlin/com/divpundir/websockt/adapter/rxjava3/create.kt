package com.divpundir.websockt.adapter.rxjava3

import com.divpundir.websockt.WebSocktFactory

public fun Rx3WebSocktClient(
    factory: WebSocktFactory
): Rx3WebSocktClient = Rx3WebSocktClientImpl(
    factory
)
