# jmap-filenode (Draft-Gated)

## Purpose

Provide typed file hierarchy APIs while FileNode remains a draft.

## Responsibilities

- `FileNode/get`, `/set`, `/changes`, `/query`, `/queryChanges`
- Draft capability detection and gated enablement

## Standards alignment

- draft-ietf-jmap-filenode (`urn:ietf:params:jmap:filenode`)

## Draft policy

- Opt-in only
- No semver stability guarantee until RFC publication
- Keep separated from RFC 9404 blob stable surface
