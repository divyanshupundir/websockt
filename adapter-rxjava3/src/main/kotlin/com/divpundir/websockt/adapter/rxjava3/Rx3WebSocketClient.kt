package com.divpundir.websockt.adapter.rxjava3

import com.divpundir.websockt.WebSocketClient
import io.reactivex.rxjava3.annotations.CheckReturnValue
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

public interface Rx3WebSocketClient {

    @get:CheckReturnValue
    public val event: Flowable<WebSocketClient.Event>

    @CheckReturnValue
    public fun open(url: String): Completable

    @CheckReturnValue
    public fun send(payload: String): Completable

    @CheckReturnValue
    public fun close(code: Int = 1000, reason: String? = null): Completable
}
