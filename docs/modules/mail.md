# jmap-mail

## Purpose

Expose typed mail APIs for RFC 8621 and selected RFC mail extensions.

## Responsibilities

- Typed models and method wrappers for `Mailbox`, `Thread`, `Email`, `EmailSubmission`, `Identity`, `VacationResponse`
- Query and change synchronization helpers for mail data types
- Extension surfaces for MDN and S/MIME verification where capabilities are present

## Standards alignment

- RFC 8621 (`urn:ietf:params:jmap:mail`, `urn:ietf:params:jmap:submission`, `urn:ietf:params:jmap:vacationresponse`)
- RFC 9007 (`urn:ietf:params:jmap:mdn`)
- RFC 9219 (`urn:ietf:params:jmap:smimeverify`)

## Boundaries

- Depends only on `jmap-core`
- No direct transport or persistence framework dependencies
