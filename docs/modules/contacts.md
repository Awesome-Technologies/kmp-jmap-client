# jmap-contacts

## Purpose

Expose typed contact and address-book APIs.

## Responsibilities

- Typed models for `AddressBook` and `ContactCard`
- Change/query helpers and capability-checked dispatch

## Standards alignment

- [RFC 9610](https://www.rfc-editor.org/rfc/rfc9610.html) (`urn:ietf:params:jmap:contacts`)

## Boundaries

- Depends only on `jmap-core`
- Domain logic only
