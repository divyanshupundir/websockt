package com.divpundir.okhttpx.websocket

import okhttp3.WebSocket

internal sealed interface WebSocketState {

    sealed class Active(val webSocket: WebSocket) : WebSocketState {
        class Connecting(webSocket: WebSocket) : Active(webSocket)
        class Open(webSocket: WebSocket) : Active(webSocket)
    }

    sealed interface Inactive : WebSocketState {
        object Closing : Inactive
        object Closed : Inactive
        object Failed : Inactive
    }
}
