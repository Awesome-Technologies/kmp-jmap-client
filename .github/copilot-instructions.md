# GitHub Copilot PR Review Instructions

This repository contains a Kotlin Multiplatform JMAP library.

## Code review guardrails

When reviewing pull requests:

- Prioritize correctness, regressions, protocol behavior, and missing tests.
- Keep comments grounded in the actual diff and changed files.
- Do not invent issues that are not supported by evidence.

Do not comment on:

- Missing imports (covered by static analysis/build checks).
- Pure formatting concerns already enforced by `ktlint` (unless the change clearly violates repository policy).

## Required review output

- Provide a concise independent summary of the PR.
- Check whether PR title and description cover all meaningful changes.
- If coverage is incomplete, explain what is missing.
- If a finding is reported, include changed file path and impact.
- If a change is docs-only or workflow-only, explicitly state no code-level findings.

## Noise reduction rules

- Avoid generic advice not tied to the diff.
- Do not speculate about unrelated modules or hypothetical features.
- If there is insufficient evidence for a concern, do not report it as a finding.

## Kotlin/KMP implementation expectations

- Prefer clear, explicit types at public boundaries.
- Preserve API compatibility expectations for library code.
- Prefer fixing root causes over suppressions.
  - Treat `@Suppress`, lint disables, and broad exclusions as last resorts.
- Keep coroutine code non-blocking.
  - Avoid blocking I/O on coroutine threads.
  - Call out blocking APIs used without appropriate dispatching.
- Error handling:
  - Prefer specific exception types over broad catches.
  - Keep `try` blocks narrow and perform data transformation outside catch-heavy blocks when possible.
- Logging and diagnostics:
  - Do not expose secrets (tokens, credentials, private keys) in logs or diagnostics.

## Repository policies to respect

- Fast-forward history policy on `main`.
- Small, bisect-safe commits.
- Commit messages follow https://cbea.ms/git-commit/
- Commit subject text starts with a capital letter after prefix tags.
- Required checks: `build`, `ktlint`, `detekt`, `unit-tests`.

## Kotlin references

- https://kotlinlang.org/docs/coding-conventions.html
- https://kotlinlang.org/docs/api-guidelines-build-for-multiplatform.html
- https://kotlinlang.org/docs/api-guidelines-introduction.html
- https://kotlinlang.org/docs/api-guidelines-backward-compatibility.html
