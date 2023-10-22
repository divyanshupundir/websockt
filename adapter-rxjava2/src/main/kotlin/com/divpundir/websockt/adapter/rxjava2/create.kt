package com.divpundir.websockt.adapter.rxjava2

import com.divpundir.websockt.WebSocktFactory

public fun Rx2WebSocktClient(
    factory: WebSocktFactory,
): Rx2WebSocktClient = Rx2WebSocktClientImpl(
    factory
)
