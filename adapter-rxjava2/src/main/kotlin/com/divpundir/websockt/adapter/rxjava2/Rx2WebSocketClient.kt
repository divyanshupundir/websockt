package com.divpundir.websockt.adapter.rxjava2

import com.divpundir.websockt.WebSocketClient
import io.reactivex.Flowable

public interface Rx2WebSocketClient {

    public val event: Flowable<WebSocketClient.Event>

    public fun open(url: String)

    public fun send(payload: String)

    public fun close(code: Int = 1000, reason: String? = null)
}
