# jmap-calendar (Draft-Gated)

## Purpose

Provide typed calendar APIs while the calendar standard remains an Internet-Draft.

## Responsibilities

- Draft-compatible typed models for `Calendar`, `CalendarEvent`, `CalendarEventParticipation`, `CalendarEventNotification`
- Draft-compatible typed wrappers for calendar and event methods
- Capability-gated dispatch for calendar draft capabilities
- Version-pinned wire-contract handling

## Standards alignment

- draft-ietf-jmap-calendars (`urn:ietf:params:jmap:calendars`)

## Draft policy

- Opt-in only
- No semver stability guarantee until RFC publication
- Revision upgrades may include breaking wire/model changes
