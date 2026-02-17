# jmap-core

## Purpose

Provide protocol and orchestration primitives used by all typed modules.

## Responsibilities

- Session discovery and capability registry (known + unknown)
- Request envelope orchestration (`using`, `methodCalls`, `callId`, `createdIds`)
- Result-reference resolution
- Push subscription and state-change orchestration
- Typed error mapping across request/method/transport layers
- Sync state coordination contracts for host-injected persistence

## Standards alignment

- RFC 8620 (`urn:ietf:params:jmap:core`)
- RFC 9749 integration touchpoints for webpush VAPID key handling

## Boundaries

- Must not depend on domain modules
- Must not depend on concrete storage frameworks
- Must keep transport implementation behind abstractions
