# Websockt

Websockt is a simple WebSocket client library for Kotlin.

It is currently in the experimental stage. It is not recommended for production use.

## Goals

- Provide a simple API for sending and receiving messages
- Manage the connection state for the user
- Automatically reconnect when the connection is lost or provide an API to do so easily
- Support major http libraries like OkHttp and Ktor
- Provide an adapter based system to support RxJava and Coroutines
