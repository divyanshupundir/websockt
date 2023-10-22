package com.divpundir.websockt.engine.okhttp

import com.divpundir.websockt.WebSockt
import com.divpundir.websockt.WebSocktFactory
import okhttp3.OkHttpClient
import okhttp3.Request

public class OkHttpWebSocktFactory(
    private val httpClient: OkHttpClient
) : WebSocktFactory {

    override fun create(
        url: String,
        onEvent: WebSockt.Event.Listener
    ): WebSockt {
        val request = Request.Builder()
            .url(url)
            .build()

        val socket = httpClient.newWebSocket(
            request,
            OkHttpWebSocktListener(onEvent)
        )

        return OkHttpWebSockt(socket)
    }
}
