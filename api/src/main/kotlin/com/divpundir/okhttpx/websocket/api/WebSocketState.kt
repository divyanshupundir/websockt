package com.divpundir.okhttpx.websocket.api

import okhttp3.WebSocket

public sealed interface WebSocketState {

    public class Active(public val webSocket: WebSocket) : WebSocketState

    public object Inactive : WebSocketState
}
