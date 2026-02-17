# Module Documentation

This directory defines responsibilities, boundaries, and capability mapping for each planned module.

## Stability policy

- RFC-backed modules are treated as stable API candidates.
- Internet-Draft-backed modules are draft-gated and must be opt-in.

## Clean Architecture and DI policy

- Domain and application contracts stay framework-agnostic.
- Infrastructure adapters are wired at composition root.
- Selected DI framework: `Koin` (module wiring in host app or dedicated composition module).

## Module index

- `core.md`: Session bootstrap, envelope model, capability and push orchestration
- `mail.md`: RFC 8621 mail surface and mail-adjacent RFC extensions
- `contacts.md`: RFC 9610 contacts model
- `blob.md`: RFC 9404 blob lifecycle and lookup
- `sharing.md`: RFC 9670 principals and sharing substrate
- `calendar-draft.md`: draft-ietf-jmap-calendars scope and draft policy
- `filenode-draft.md`: draft-ietf-jmap-filenode scope and draft policy
- `realtime-bindings.md`: optional transport bindings for push/event delivery
- `testing.md`: cross-module and contract test support expectations
