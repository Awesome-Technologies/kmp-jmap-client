# TODO

## Milestone 1: documentation and governance bootstrap

- [x] Create `AGENTS.md` with strict governance policy
- [x] Add PR workflow with required checks (`build`, `ktlint`, `detekt`, `unit-tests`)
- [x] Add release-test workflow for `main`
- [x] Add `README.md` with architecture and process summary
- [x] Add Apache 2.0 `LICENSE`
- [x] Add human contribution guide (`CONTRIBUTING.md`)

## Milestone 2: architecture docs package

- [ ] Create extensive `ARCHITECTURE.md`
- [ ] Create module docs under `docs/modules/`
- [ ] Create authenticated examples documentation under `docs/examples/`

## Milestone 3: test-driven scaffold

- [ ] Initialize Gradle Kotlin Multiplatform project
- [ ] Add `ktlint` plugin and baseline config
- [ ] Add `detekt` plugin and baseline config
- [ ] Add unit test setup for common/JVM/JS/iOS targets
- [ ] Add Dokka setup for API docs generation

## Milestone 4: TDD implementation phase

- [ ] Write failing tests for core protocol envelope and session handling
- [ ] Implement minimal `jmap-core` to satisfy tests
- [ ] Write failing tests for `jmap-mail`
- [ ] Implement minimal `jmap-mail`
- [ ] Write failing tests for `jmap-calendar`
- [ ] Implement minimal `jmap-calendar`
- [ ] Write failing tests for `jmap-contacts`
- [ ] Implement minimal `jmap-contacts`
- [ ] Add raw escape hatch tests and implementation
- [ ] Add cross-target smoke tests

## Milestone 5: docs publishing and hardening

- [ ] Add GitHub Pages workflow for Dokka publishing
- [ ] Add branch protection using required checks
- [ ] Add compatibility validator for library API stability
- [ ] Add release documentation and versioning policy
