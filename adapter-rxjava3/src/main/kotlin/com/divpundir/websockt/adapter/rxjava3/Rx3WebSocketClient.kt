package com.divpundir.websockt.adapter.rxjava3

import com.divpundir.websockt.WebSocketClient
import io.reactivex.rxjava3.core.Flowable

public interface Rx3WebSocketClient {

    public val event: Flowable<WebSocketClient.Event>

    public fun open(url: String)

    public fun send(payload: String)

    public fun close(code: Int = 1000, reason: String? = null)
}
