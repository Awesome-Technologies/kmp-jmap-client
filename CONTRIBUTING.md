# Contributing

Thank you for contributing.
This project is currently in documentation-first and test-driven bootstrap mode.

## Scope of contributions right now

Until architecture review is complete, contributions should focus on:

- documentation quality
- CI/workflow hardening
- test strategy and scaffolding

Avoid starting production implementation unless explicitly requested by maintainers.

## Required policies

All AI contributions must follow `AGENTS.md`.

Key rules:

- *fast-forward history only* for `main`
- no merge commits into `main`
- small, single-purpose commits
- if an earlier commit has an issue, create a fixup commit (`git commit --fixup <commit>`) and rebase before submitting a PR
- no broken commits

## Mandatory quality gates

Before pushing, ensure all required checks pass:

- build
- `ktlint`
- `detekt`
- unit tests

If one gate fails, fix it before creating/pushing a commit.

## Commit message expectations

Commit messages should describe:

- what behavior changed
- why it changed
- evidence of validation (tests/checks)
- and follow https://cbea.ms/git-commit/

Documentation hygiene hint:

- Before pushing a commit, review top-level Markdown docs for consistency with your changes.
- If your change affects a module, also review that module-level Markdown docs and update them when needed.

Example:

```text
feat(core): Add session model parsing

- Parse capabilities and account map from session response
- Preserve unknown JSON fields for extension safety
- Validation: ./gradlew test ktlintCheck detekt
```

## Pull request process

### Contributor steps

1. Rebase your branch onto `main`.
2. Ensure commits are reviewable and bisect-safe.
3. Fill out the PR template completely, especially scope, validation, and risk sections.
4. Ensure required CI jobs are green (`build`, `ktlint`, `detekt`, `unit-tests`).
5. Request review with a concise summary and test evidence.

### Maintainer steps

1. Confirm PR scope is clear and matches the changed files.
2. Confirm commit history quality (small-step, understandable, bisect-safe).
3. Confirm required CI checks are green (`build`, `ktlint`, `detekt`, `unit-tests`).
4. Confirm documentation updates are present when required (top-level and affected module-level markdown).
5. Merge using fast-forward compatible strategy only.

## Style and best practices

Follow the latest official Kotlin guidance:

- [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- [Building a Kotlin library for multiplatform](https://kotlinlang.org/docs/api-guidelines-build-for-multiplatform.html)
- [Kotlin library authors' guidelines (introduction)](https://kotlinlang.org/docs/api-guidelines-introduction.html)
- [Backward compatibility guidelines for library authors](https://kotlinlang.org/docs/api-guidelines-backward-compatibility.html)

## Security reporting

Do not disclose security vulnerabilities publicly in issues.
Share details privately with project maintainers.
