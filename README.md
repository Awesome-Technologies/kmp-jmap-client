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
- PR review by GitHub Copilot
- Release-confidence workflow on every push to `main`
- Regular security audits (CodeQL, Dependabot, Code Scanning, Secret Scanning)
- Planned GitHub Pages API docs publication with Dokka

## Authentication in examples

All repository examples and docs must include authentication.
Unauthenticated example flows are out of scope.
