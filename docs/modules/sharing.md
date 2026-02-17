# jmap-sharing

## Purpose

Provide sharing and principal abstractions reusable across domains.

## Responsibilities

- Principal retrieval/query/change wrappers
- Sharing rights and `shareWith` policy mapping
- Notification model mapping for share-related changes

## Standards alignment

- [RFC 9670](https://www.rfc-editor.org/rfc/rfc9670.html) (`urn:ietf:params:jmap:principals`, `urn:ietf:params:jmap:principals:owner`)

## Boundaries

- Depends only on `jmap-core`
- Must remain domain-agnostic where possible
