# JMAP Kotlin Multiplatform

[![PR Checks](https://github.com/Awesome-Technologies/kmp-jmap-client/actions/workflows/pr.yml/badge.svg)](https://github.com/Awesome-Technologies/kmp-jmap-client/actions/workflows/pr.yml)
[![Release Test](https://github.com/Awesome-Technologies/kmp-jmap-client/actions/workflows/release-test.yml/badge.svg)](https://github.com/Awesome-Technologies/kmp-jmap-client/actions/workflows/release-test.yml)
[![License: Apache-2.0](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)

Kotlin Multiplatform library for the JMAP protocol with typed APIs for Core, Mail, Calendar, and Contacts, plus a raw escape hatch for unsupported extensions.

## Current status

This repository is in a docs-first and test-driven bootstrap phase.
Implementation starts only after architecture and process docs are reviewed.

License: [Apache License 2.0](LICENSE)

## Plan at a glance

- Targets: JVM, Android, iOS, JS
- API model: typed-first APIs plus raw request/response escape hatch
- Auth: caller-supplied headers (`HeaderProvider` style), no built-in OAuth flow
- Extension handling: preserve unknown JSON fields for forward compatibility
- Realtime: optional module (not required for initial core release)

## Architecture plan

The planned module split is:

- `jmap-core`
- `jmap-mail`
- `jmap-calendar`
- `jmap-contacts`
- `jmap-realtime` (optional)
- `jmap-testing`

Detailed architecture and design decisions live in `ARCHITECTURE.md`.

## Quality and delivery model

- Test-driven implementation (red -> green -> refactor)
- Small, single-purpose commits
- Fast-forward history policy
- Mandatory quality gates per commit:
  - build
  - `ktlint`
  - `detekt`
  - unit tests

See `AGENTS.md` and `CONTRIBUTING.md` for full contribution policy.

## CI and GitHub operations

- PR validation workflow with required checks
- PR triage and labeling by GitHub Copilot workflow
- PR review by GitHub Copilot
- Release-confidence workflow on every push to `main`
- Regular security audits (CodeQL, Dependabot, Code Scanning, Secret Scanning)
- Planned GitHub Pages API docs publication with Dokka

## Authentication in examples

All repository examples and docs must include authentication.
Unauthenticated example flows are out of scope.

## JMAP RFCs

Comprehensive list of published JMAP Working Group RFCs.
Status badges are sourced from IETF Datatracker (`doc.json` `std_level`) so they auto-refresh from the upstream document metadata.

| RFC | Title | Status |
| --- | --- | --- |
| [RFC 8620](https://www.rfc-editor.org/rfc/rfc8620.html) | The JSON Meta Application Protocol (JMAP) | ![RFC 8620 status][rfc8620-status] |
| [RFC 8621](https://www.rfc-editor.org/rfc/rfc8621.html) | The JSON Meta Application Protocol (JMAP) for Mail | ![RFC 8621 status][rfc8621-status] |
| [RFC 8887](https://www.rfc-editor.org/rfc/rfc8887.html) | A JSON Meta Application Protocol (JMAP) Subprotocol for WebSocket | ![RFC 8887 status][rfc8887-status] |
| [RFC 9007](https://www.rfc-editor.org/rfc/rfc9007.html) | Handling Message Disposition Notification with the JSON Meta Application Protocol (JMAP) | ![RFC 9007 status][rfc9007-status] |
| [RFC 9219](https://www.rfc-editor.org/rfc/rfc9219.html) | S/MIME Signature Verification Extension to the JSON Meta Application Protocol (JMAP) | ![RFC 9219 status][rfc9219-status] |
| [RFC 9404](https://www.rfc-editor.org/rfc/rfc9404.html) | JSON Meta Application Protocol (JMAP) Blob Management Extension | ![RFC 9404 status][rfc9404-status] |
| [RFC 9425](https://www.rfc-editor.org/rfc/rfc9425.html) | JSON Meta Application Protocol (JMAP) for Quotas | ![RFC 9425 status][rfc9425-status] |
| [RFC 9610](https://www.rfc-editor.org/rfc/rfc9610.html) | JSON Meta Application Protocol (JMAP) for Contacts | ![RFC 9610 status][rfc9610-status] |
| [RFC 9661](https://www.rfc-editor.org/rfc/rfc9661.html) | The JSON Meta Application Protocol (JMAP) for Sieve Scripts | ![RFC 9661 status][rfc9661-status] |
| [RFC 9670](https://www.rfc-editor.org/rfc/rfc9670.html) | JSON Meta Application Protocol (JMAP) Sharing | ![RFC 9670 status][rfc9670-status] |
| [RFC 9749](https://www.rfc-editor.org/rfc/rfc9749.html) | Use of Voluntary Application Server Identification (VAPID) in JSON Meta Application Protocol (JMAP) Web Push | ![RFC 9749 status][rfc9749-status] |

## JMAP Internet-Drafts

Comprehensive list of JMAP Working Group Internet-Drafts from the [IETF Datatracker JMAP document index](https://datatracker.ietf.org/group/jmap/documents/) (active, active with IESG, and expired, checked on February 17, 2026).
Status badges are sourced from IETF Datatracker (`doc.json` `state`) so they auto-refresh from upstream metadata.

| Draft | Title | Status |
| --- | --- | --- |
| [draft-ietf-jmap-calendars](https://datatracker.ietf.org/doc/draft-ietf-jmap-calendars/) | JSON Meta Application Protocol (JMAP) for Calendars | ![draft-ietf-jmap-calendars status][draft-ietf-jmap-calendars-status] |
| [draft-ietf-jmap-emailpush](https://datatracker.ietf.org/doc/draft-ietf-jmap-emailpush/) | JSON Meta Application Protocol (JMAP) Email Delivery Push Notifications | ![draft-ietf-jmap-emailpush status][draft-ietf-jmap-emailpush-status] |
| [draft-ietf-jmap-essential](https://datatracker.ietf.org/doc/draft-ietf-jmap-essential/) | JMAP Essential Profile | ![draft-ietf-jmap-essential status][draft-ietf-jmap-essential-status] |
| [draft-ietf-jmap-filenode](https://datatracker.ietf.org/doc/draft-ietf-jmap-filenode/) | JMAP File Storage extension | ![draft-ietf-jmap-filenode status][draft-ietf-jmap-filenode-status] |
| [draft-ietf-jmap-mail-sharing](https://datatracker.ietf.org/doc/draft-ietf-jmap-mail-sharing/) | JMAP Mail Sharing | ![draft-ietf-jmap-mail-sharing status][draft-ietf-jmap-mail-sharing-status] |
| [draft-ietf-jmap-metadata](https://datatracker.ietf.org/doc/draft-ietf-jmap-metadata/) | JMAP Object Metadata | ![draft-ietf-jmap-metadata status][draft-ietf-jmap-metadata-status] |
| [draft-ietf-jmap-portability-extensions](https://datatracker.ietf.org/doc/draft-ietf-jmap-portability-extensions/) | JMAP Migration and Portability Extensions | ![draft-ietf-jmap-portability-extensions status][draft-ietf-jmap-portability-extensions-status] |
| [draft-ietf-jmap-refplus](https://datatracker.ietf.org/doc/draft-ietf-jmap-refplus/) | JMAP Enhanced Result References | ![draft-ietf-jmap-refplus status][draft-ietf-jmap-refplus-status] |
| [draft-ietf-jmap-rest](https://datatracker.ietf.org/doc/draft-ietf-jmap-rest/) | JMAP REST Mapping | ![draft-ietf-jmap-rest status][draft-ietf-jmap-rest-status] |
| [draft-ietf-jmap-smime-sender-extensions](https://datatracker.ietf.org/doc/draft-ietf-jmap-smime-sender-extensions/) | JMAP extension for S/MIME signing and encryption | ![draft-ietf-jmap-smime-sender-extensions status][draft-ietf-jmap-smime-sender-extensions-status] |
| [draft-ietf-jmap-tasks](https://datatracker.ietf.org/doc/draft-ietf-jmap-tasks/) | JMAP for Tasks | ![draft-ietf-jmap-tasks status][draft-ietf-jmap-tasks-status] |

[rfc8620-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Frfc8620%2Fdoc.json&query=%24.std_level&label=status&cacheSeconds=86400
[rfc8621-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Frfc8621%2Fdoc.json&query=%24.std_level&label=status&cacheSeconds=86400
[rfc8887-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Frfc8887%2Fdoc.json&query=%24.std_level&label=status&cacheSeconds=86400
[rfc9007-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Frfc9007%2Fdoc.json&query=%24.std_level&label=status&cacheSeconds=86400
[rfc9219-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Frfc9219%2Fdoc.json&query=%24.std_level&label=status&cacheSeconds=86400
[rfc9404-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Frfc9404%2Fdoc.json&query=%24.std_level&label=status&cacheSeconds=86400
[rfc9425-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Frfc9425%2Fdoc.json&query=%24.std_level&label=status&cacheSeconds=86400
[rfc9610-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Frfc9610%2Fdoc.json&query=%24.std_level&label=status&cacheSeconds=86400
[rfc9661-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Frfc9661%2Fdoc.json&query=%24.std_level&label=status&cacheSeconds=86400
[rfc9670-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Frfc9670%2Fdoc.json&query=%24.std_level&label=status&cacheSeconds=86400
[rfc9749-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Frfc9749%2Fdoc.json&query=%24.std_level&label=status&cacheSeconds=86400
[draft-ietf-jmap-calendars-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Fdraft-ietf-jmap-calendars%2Fdoc.json&query=%24.state&label=status&cacheSeconds=86400
[draft-ietf-jmap-emailpush-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Fdraft-ietf-jmap-emailpush%2Fdoc.json&query=%24.state&label=status&cacheSeconds=86400
[draft-ietf-jmap-essential-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Fdraft-ietf-jmap-essential%2Fdoc.json&query=%24.state&label=status&cacheSeconds=86400
[draft-ietf-jmap-filenode-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Fdraft-ietf-jmap-filenode%2Fdoc.json&query=%24.state&label=status&cacheSeconds=86400
[draft-ietf-jmap-mail-sharing-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Fdraft-ietf-jmap-mail-sharing%2Fdoc.json&query=%24.state&label=status&cacheSeconds=86400
[draft-ietf-jmap-metadata-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Fdraft-ietf-jmap-metadata%2Fdoc.json&query=%24.state&label=status&cacheSeconds=86400
[draft-ietf-jmap-portability-extensions-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Fdraft-ietf-jmap-portability-extensions%2Fdoc.json&query=%24.state&label=status&cacheSeconds=86400
[draft-ietf-jmap-refplus-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Fdraft-ietf-jmap-refplus%2Fdoc.json&query=%24.state&label=status&cacheSeconds=86400
[draft-ietf-jmap-rest-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Fdraft-ietf-jmap-rest%2Fdoc.json&query=%24.state&label=status&cacheSeconds=86400
[draft-ietf-jmap-smime-sender-extensions-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Fdraft-ietf-jmap-smime-sender-extensions%2Fdoc.json&query=%24.state&label=status&cacheSeconds=86400
[draft-ietf-jmap-tasks-status]: https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdatatracker.ietf.org%2Fdoc%2Fdraft-ietf-jmap-tasks%2Fdoc.json&query=%24.state&label=status&cacheSeconds=86400
