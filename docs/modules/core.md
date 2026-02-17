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

- [RFC 8620](https://www.rfc-editor.org/rfc/rfc8620.html) (`urn:ietf:params:jmap:core`)
- [RFC 9749](https://www.rfc-editor.org/rfc/rfc9749.html) integration touchpoints for webpush VAPID key handling (`urn:ietf:params:jmap:webpush-vapid`)

## Boundaries

- Must not depend on domain modules
- Must not depend on concrete storage frameworks
- Must keep transport implementation behind abstractions
- Must remain DI-framework agnostic (no direct Koin dependency in pure core policy types)

## Composition and DI

- `jmap-core` exposes ports and constructor-injectable services.
- `Koin` modules bind interface contracts to infrastructure implementations at composition root.
