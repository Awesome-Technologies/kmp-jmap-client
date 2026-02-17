# AGENTS.md

This document defines mandatory contribution and delivery rules for this repository.

## Governance Model

- `AGENTS.md` is normative for contribution process requirements.
- Any workflow or process change that affects contribution policy must update this file in the same pull request.

## Branching and History Policy

- Default branch is `main`.
- `main` accepts only fast-forward compatible integration.
- Merge commits into `main` are not allowed.
- Keep commit history linear and bisect-friendly.
- If you detect errors introduced by earlier commits in your branch, create fixup commits (`git commit --fixup <commit>`) instead of rewriting history, so commits can be reordered/squashed later by the maintainer before sending a PR.

## Commit Quality Standard

Every commit must be independently understandable, reviewable, and safe to bisect.

- Make small, single-purpose commits.
- Do not mix unrelated refactors and behavior changes in one commit.
- Avoid speculative WIP commits on shared branches.
- Commit messages must follow https://cbea.ms/git-commit/ and describe the behavior-level change plus test proof (for example, which checks passed).
- Commit subject text must start with a capital letter after any prefix tags (for example, `feat(core): Add session parsing`, not `feat(core): add session parsing`).
- For every commit, check all top-level Markdown documents for consistency and required updates.
- For every commit that touches a module, also check that module-level Markdown documents for consistency and required updates.

## Mandatory Pre-Commit Quality Gates

A commit must not be created or pushed unless all required checks pass.

Required gates:

- Build passes.
- `ktlint` passes.
- `detekt` passes.
- Unit tests pass.

## No Broken Commit Rule

- Do not create or push commits that fail any required quality gate.
- If a gate fails, fix the issue before committing.

## Pull Request and Merge Requirements

- PR checks must be green before merge.
- Required CI jobs are:
  - `build`
  - `ktlint`
  - `detekt`
  - `unit-tests`
- Repository branch protection must mark these jobs as required.

## Release Confidence Requirements

Release validation must include the same quality gates as local/PR checks, plus cross-target confidence checks.

## Security Audit Baseline

Run recurring security audits with:

- CodeQL analysis
- dependency vulnerability audit
- secret scan

## Decision and Review Quality

- Changes must be independently understandable.
- Each commit should preserve a working state.
- Reviewers should be able to evaluate each commit in isolation.

## Kotlin Style and Best Practices References

Agents must follow the latest guidance from official Kotlin documentation:

- [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- [Building a Kotlin library for multiplatform](https://kotlinlang.org/docs/api-guidelines-build-for-multiplatform.html)
- [Kotlin library authors' guidelines (introduction)](https://kotlinlang.org/docs/api-guidelines-introduction.html)
- [Backward compatibility guidelines for library authors](https://kotlinlang.org/docs/api-guidelines-backward-compatibility.html)
