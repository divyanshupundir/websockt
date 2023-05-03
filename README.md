# Websockt

Websockt is a simple WebSocket client library for Kotlin.

## Overview

Apart from sending and receiving messages, Websockt provides the following features:
- WebSocket connection state management
- A simple `onFailure` callback to implement custom reconnection strategies
- Abstraction over different HTTP libraries (currently OkHttp) so that you can fit it with your existing stack
- Lightweight RxJava and Coroutines adapters
