package com.divpundir.websockt.adapter.rxjava2

import com.divpundir.websockt.WebSocktClient
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.annotations.CheckReturnValue

public interface Rx2WebSocktClient {

    @get:CheckReturnValue
    public val event: Flowable<WebSocktClient.Event>

    @CheckReturnValue
    public fun open(url: String): Completable

    @CheckReturnValue
    public fun send(payload: String): Completable

    @CheckReturnValue
    public fun close(code: Int = 1000, reason: String? = null): Completable
}
