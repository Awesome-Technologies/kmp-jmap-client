# jmap-blob

## Purpose

Expose typed blob lifecycle operations as a stable module surface.

## Responsibilities

- `Blob/upload`, `Blob/get`, and `Blob/lookup`
- Mapping of blob-to-object reference lookups

## Standards alignment

- RFC 9404 (`urn:ietf:params:jmap:blob`)

## Boundaries

- RFC-stable module distinct from draft `FileNode` APIs
- Depends only on `jmap-core`
