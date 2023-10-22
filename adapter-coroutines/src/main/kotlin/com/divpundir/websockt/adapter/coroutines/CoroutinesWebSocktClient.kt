package com.divpundir.websockt.adapter.coroutines

import com.divpundir.websockt.WebSocktClient
import kotlinx.coroutines.flow.SharedFlow

public interface CoroutinesWebSocktClient {

    public val event: SharedFlow<WebSocktClient.Event>

    public suspend fun open(url: String)

    public suspend fun send(payload: String)

    public suspend fun close(code: Int = 1000, reason: String? = null)
}
