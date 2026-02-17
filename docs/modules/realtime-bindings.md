# jmap-realtime-bindings (Optional)

## Purpose

Provide optional transport bindings for event delivery and low-latency updates.

## Responsibilities

- WebSocket/event-stream channel setup and lifecycle
- Binding-specific event decoding
- Mapping binding events into core push/state-change model

## Standards alignment

- RFC 8887 (`urn:ietf:params:jmap:websocket`)

## Boundaries

- Transport binding only
- Core push subscription model remains in `jmap-core`
