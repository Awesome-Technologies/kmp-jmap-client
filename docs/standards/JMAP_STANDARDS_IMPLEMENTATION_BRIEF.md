# JMAP Standards Implementation Brief (RFCs + Internet-Drafts)

Last reviewed: 2026-02-17

This document summarizes the current JSON Mail Access Protocol (JMAP) working group standards set for implementation agents.

## Scope and intent

- Provide a fast, implementation-oriented map of JMAP standards.
- Highlight capability URNs, core data types, and method surface.
- Separate stable RFC requirements from draft/experimental work.

## Standards inventory

### Published RFCs (stable)

1. RFC 8620 - The JSON Meta Application Protocol (JMAP)
2. RFC 8621 - The JSON Meta Application Protocol (JMAP) for Mail
3. RFC 8887 - A JSON Meta Application Protocol (JMAP) Subprotocol for WebSocket
4. RFC 9007 - Handling Message Disposition Notification with JMAP
5. RFC 9219 - S/MIME Signature Verification Extension to JMAP
6. RFC 9404 - JMAP Blob Management Extension (updates RFC 8620)
7. RFC 9425 - JMAP for Quotas
8. RFC 9610 - JMAP for Contacts
9. RFC 9661 - JMAP for Sieve Scripts
10. RFC 9670 - JMAP Sharing (updates RFC 8620)
11. RFC 9749 - VAPID for JMAP Web Push

### Internet-Drafts in JMAP WG (work in progress)

Active:

1. draft-ietf-jmap-emailpush-02
2. draft-ietf-jmap-filenode-03
3. draft-ietf-jmap-mail-sharing-00
4. draft-ietf-jmap-metadata-01
5. draft-ietf-jmap-refplus-01

Active with IESG / RFC Editor queue:

1. draft-ietf-jmap-calendars-26

Expired:

1. draft-ietf-jmap-essential-01
2. draft-ietf-jmap-portability-extensions-01
3. draft-ietf-jmap-rest-00
4. draft-ietf-jmap-smime-sender-extensions-04
5. draft-ietf-jmap-tasks-06

## Protocol baseline all agents should internalize first

### RFC 8620 (Core)

Capability:

- `urn:ietf:params:jmap:core`

Core model:

- Session discovery is mandatory. Parse `apiUrl`, `downloadUrl`, `uploadUrl`, `eventSourceUrl`, and account capability map.
- The JMAP request envelope supports ordered `methodCalls` plus result references to previous call outputs.
- Standard method families are `/get`, `/changes`, `/set`, `/query`, `/queryChanges`, and `/copy`.
- Support optimistic concurrency via `ifInState` for `/set` and state tokens from `/changes` and `/queryChanges`.
- Keep `createdIds` mapping stable within chained requests.
- Push is part of core (`PushSubscription/*`, `StateChange`, EventSource endpoint).

Implementation priorities:

- Build one generic method execution abstraction that powers every data type.
- Enforce strict result-reference resolution and clear request-level vs method-level error handling.
- Treat blob transfer (upload/download) as separate from structured API calls.

### RFC 8621 (Mail)

Capabilities:

- `urn:ietf:params:jmap:mail`
- `urn:ietf:params:jmap:submission`
- `urn:ietf:params:jmap:vacationresponse`

Primary data types:

- `Mailbox`, `Thread`, `Email`, `SearchSnippet`, `Identity`, `EmailSubmission`, `VacationResponse`

Primary methods:

- Mailbox: `Mailbox/get`, `/changes`, `/query`, `/queryChanges`, `/set`
- Thread: `Thread/get`, `/changes`
- Email: `Email/get`, `/changes`, `/query`, `/queryChanges`, `/set`, `/copy`, `/import`, `/parse`
- Search: `SearchSnippet/get`
- Submission: `EmailSubmission/get`, `/changes`, `/query`, `/queryChanges`, `/set`
- Identity: `Identity/get`, `/changes`, `/set`
- Vacation response: `VacationResponse/get`, `/set`

Implementation notes:

- Support server push type `EmailDelivery` semantics where advertised.
- Preserve message structure fidelity: headers, body parts, attachments, keywords, mailbox membership.
- Implement submission pipeline with identity policy enforcement and submission errors (`forbiddenFrom`, `forbiddenToSend`, etc.).

### RFC 8887 (WebSocket binding)

Capability:

- `urn:ietf:params:jmap:websocket`

Key points:

- Session capability provides websocket URL and `supportsPush`.
- WebSocket handshake must negotiate subprotocol `jmap`.
- Message types include `Request`, `Response`, `RequestError`, and push-related control messages.
- Push enable/disable over socket uses `WebSocketPushEnable` and `WebSocketPushDisable` with `StateChange` notifications.

Implementation notes:

- Keep HTTP and WebSocket transports interchangeable above transport layer.
- Correlate `requestId` and request-scoped errors cleanly.

### RFC 9007 (MDN)

Capability:

- `urn:ietf:params:jmap:mdn`

Core additions:

- `MDN/send`: creates and sends disposition notifications and coordinates keyword updates.
- `MDN/parse`: parses MDN blobs into structured MDN object values.
- New error code: `mdnAlreadySent`.

Implementation notes:

- Enforce `$mdnsent` update requirements exactly when sending MDNs.
- Handle parse outcomes: `parsed`, `notFound`, and non-parsable blobs.

### RFC 9219 (S/MIME verify)

Capability:

- `urn:ietf:params:jmap:smimeverify`

Core additions:

- Extends `Email/get` with verification fields including `smimeStatus`, `smimeStatusAtDelivery`, `smimeErrors`, `smimeVerifiedAt`.
- Extends `Email/query` filters with S/MIME-related predicates.
- Defines how S/MIME status changes should (or should not) affect `Email/changes`.

Implementation notes:

- This extension assumes trust in server-side verification.
- Treat expensive verification paths as cache-sensitive.

### RFC 9404 (Blob management)

Capability:

- `urn:ietf:params:jmap:blob`

Core additions:

- `Blob/upload` for inline blob creation in method call chains.
- `Blob/get` to retrieve raw/encoded blob data in JMAP method responses.
- `Blob/lookup` reverse mapping from blob ID to referencing object IDs by data type.
- New error code: `unknownDataType`.

Implementation notes:

- Use this to reduce round trips in attachment-heavy flows.
- Ensure blob reference tracking remains consistent across mail/contact/calendar object stores.

### RFC 9425 (Quotas)

Capability:

- `urn:ietf:params:jmap:quota`

Core additions:

- `Quota` data type with standard retrieval/query/change methods.
- Quota values are exposed by scope and resource type.

Implementation notes:

- Integrate with submission/upload/create guards to fail fast client-side.

### RFC 9610 (Contacts)

Capability:

- `urn:ietf:params:jmap:contacts`

Core model:

- `AddressBook` and `ContactCard` data model for synchronized contact storage.
- Standard JMAP method suite on contacts/address books.

Implementation notes:

- Keep JSContact mapping fidelity high.
- Preserve sharing-aware fields (`shareWith`, `myRights`) when present.

### RFC 9661 (Sieve scripts)

Capability:

- `urn:ietf:params:jmap:sieve`

Core additions:

- `SieveScript` management (`get`, `set`, `query`) and validation (`SieveScript/validate`).
- Capability advertises script limits and supported Sieve extensions.

Implementation notes:

- Expose server limits and extension support through typed capability model.
- Prefer preflight validate before script activation/update UX.

### RFC 9670 (Sharing)

Capabilities:

- `urn:ietf:params:jmap:principals`
- `urn:ietf:params:jmap:principals:owner`

Core additions:

- `Principal` data type and methods (`Principal/get`, `/changes`, `/set`, `/query`).
- Generalized sharing model (`shareWith`, rights maps, notification model).
- `ShareNotification` data type.

Implementation notes:

- This is the cross-domain sharing foundation for mail, calendars, contacts, and other types.
- Keep rights and principal-subscription handling in a reusable policy layer.

### RFC 9749 (VAPID for web push)

Capability:

- `urn:ietf:params:jmap:webpush-vapid`

Core additions:

- Session capability carries `applicationServerKey` for VAPID-enabled push services.
- Clients use key material during push subscription registration.
- Explicit key rotation behavior tied to session state changes.

Implementation notes:

- Monitor session changes and recreate push subscriptions on key changes.
- Handle race/retry safety around `PushSubscription/set` and session refresh.

## Internet-Draft implementation summary

### Active drafts worth tracking closely

1. draft-ietf-jmap-calendars-26
   - Comprehensive calendar/event synchronization model.
   - New capabilities include `urn:ietf:params:jmap:calendars`, plus availability and parse-related capabilities.
   - Adds data types and methods such as `Calendar/*`, `CalendarEvent/*`, participant identity methods, notification methods, recurrence and scheduling semantics.
   - In IESG / RFC Editor path; highest probability near-term standard.

2. draft-ietf-jmap-emailpush-02
   - Capability: `urn:ietf:params:jmap:emailpush`.
   - Adds filtered delivery push objects to avoid full resync for notification UX.
   - Lets clients request selected email properties in push payloads.

3. draft-ietf-jmap-filenode-03
   - Capability: `urn:ietf:params:jmap:filenode`.
   - Defines `FileNode` hierarchy and methods (`FileNode/get`, `/set`, `/changes`, `/query`, `/queryChanges`).
   - Explicitly positioned as JMAP replacement path for WebDAV-style remote filesystem access.

4. draft-ietf-jmap-mail-sharing-00
   - Capability: `urn:ietf:params:jmap:mail:share`.
   - Applies RFC 9670 sharing model to RFC 8621 mailbox objects.

5. draft-ietf-jmap-metadata-01
   - Capability: `urn:ietf:params:jmap:metadata`.
   - Generic metadata/annotation layer for JMAP objects.
   - Defines `Metadata` object model and methods (`Metadata/get`, `/set`, `/changes`, `/query`, `/queryChanges`).

6. draft-ietf-jmap-refplus-01
   - Capability: `urn:ietf:params:jmap:refplus`.
   - Extends result references into additional contexts (`/set` patch objects and `/query` filters), with JSONPath support.

### Expired drafts (use for ideas, not normative behavior)

1. draft-ietf-jmap-essential-01
   - Guidance-oriented "essential profile" for lower-complexity JMAP adoption.

2. draft-ietf-jmap-portability-extensions-01
   - Migration/portability extensions (server/environment detail exposure and response logging for diagnostics).

3. draft-ietf-jmap-rest-00
   - REST mapping for JMAP-style operations.

4. draft-ietf-jmap-smime-sender-extensions-04
   - Sender-side S/MIME signing/encryption and automatic decryption concepts.

5. draft-ietf-jmap-tasks-06
   - Task synchronization data model over JMAP.

## Dependency graph for implementation planning

1. Mandatory base: RFC 8620.
2. Core feature plane: RFC 8621 (mail).
3. Transport/push options: RFC 8887 + RFC 9749.
4. Mail-adjacent extensions: RFC 9007, RFC 9219, RFC 9404, RFC 9425, RFC 9661.
5. Cross-domain data + sharing: RFC 9610 + RFC 9670.
6. Near-term likely additions: draft calendars + active drafts listed above.

## Recommended phased rollout for this repository

### Phase 1 (production baseline)

- RFC 8620 + RFC 8621
- RFC 8887 (optional transport layer behind interface)
- RFC 9404 (blob efficiency)

### Phase 2 (operational completeness)

- RFC 9425 (quota awareness)
- RFC 9007 (MDN)
- RFC 9219 (S/MIME verify metadata)
- RFC 9661 (Sieve management)

### Phase 3 (collaboration and address books)

- RFC 9670 (sharing substrate)
- RFC 9610 (contacts)

### Phase 4 (push hardening)

- RFC 9749 (VAPID key advertisement/rotation support)

### Phase 5 (draft incubation behind feature flags)

- Calendars, EmailPush, FileNode, Metadata, RefPlus, Mail sharing
- Keep wire compatibility gates per draft version number and capability checks.

## Agent implementation checklist

1. Parse Session and capabilities into typed feature flags.
2. Build one generic JMAP method-call engine with:
   - result-reference resolution
   - typed request/response envelopes
   - method-level and request-level error mapping
3. Implement state-token based sync loops (`/changes`, `/queryChanges`) for every data type.
4. Centralize capability-gated method registration to avoid accidental unsupported calls.
5. Normalize rights/sharing handling (`myRights`, `shareWith`, principal data) for re-use across modules.
6. Keep transport abstraction clean: HTTP and WebSocket should share method execution path.
7. Treat drafts as optional modules with strict capability/version gating.

## Security and reliability themes across specs

- Always require authenticated transport and strict JSON validation.
- Implement bounded request sizes and explicit retry behavior for push and submission flows.
- Enforce optimistic concurrency (`ifInState`) where supported to avoid silent overwrites.
- Keep key-rotation/session-state reaction logic robust for push subsystems.
- Surface quota and rights failures as first-class typed errors for caller handling.

## Primary references

JMAP WG index:

- https://datatracker.ietf.org/group/jmap/documents/

RFCs:

- https://datatracker.ietf.org/doc/rfc8620/
- https://datatracker.ietf.org/doc/rfc8621/
- https://datatracker.ietf.org/doc/rfc8887/
- https://datatracker.ietf.org/doc/rfc9007/
- https://datatracker.ietf.org/doc/rfc9219/
- https://datatracker.ietf.org/doc/rfc9404/
- https://datatracker.ietf.org/doc/rfc9425/
- https://datatracker.ietf.org/doc/rfc9610/
- https://datatracker.ietf.org/doc/rfc9661/
- https://datatracker.ietf.org/doc/rfc9670/
- https://datatracker.ietf.org/doc/rfc9749/

Internet-Drafts:

- https://datatracker.ietf.org/doc/draft-ietf-jmap-calendars/
- https://datatracker.ietf.org/doc/draft-ietf-jmap-emailpush/
- https://datatracker.ietf.org/doc/draft-ietf-jmap-filenode/
- https://datatracker.ietf.org/doc/draft-ietf-jmap-mail-sharing/
- https://datatracker.ietf.org/doc/draft-ietf-jmap-metadata/
- https://datatracker.ietf.org/doc/draft-ietf-jmap-refplus/
- https://datatracker.ietf.org/doc/draft-ietf-jmap-essential/
- https://datatracker.ietf.org/doc/draft-ietf-jmap-portability-extensions/
- https://datatracker.ietf.org/doc/draft-ietf-jmap-rest/
- https://datatracker.ietf.org/doc/draft-ietf-jmap-smime-sender-extensions/
- https://datatracker.ietf.org/doc/draft-ietf-jmap-tasks/
