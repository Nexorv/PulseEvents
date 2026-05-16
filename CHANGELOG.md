# Changelog

## 1.0.0 - 2026-05-03

### Added

- Data-driven custom event system under `custom-events` in `config.yml`.
- Configurable event metadata: `name`, `chance`, `duration`, `icon`, `min-players`, `allowed-worlds`, `start-message`, `end-message`, and `bossbar-title`.
- Configurable action pipeline with delayed, repeating, targeted, and chance-gated actions.
- Supported custom action types:
  - `message`
  - `sound`
  - `potion`
  - `teleport`
  - `spawn-mob`
  - `strike-lightning`
  - `spawn-tnt`
  - `velocity`
  - `ignite`
  - `economy-reward`
  - `title`
  - `console-command`
  - `player-command`
- New action fields for custom events:
  - `target-count`
  - `chance`
  - `fade-in-ticks`
  - `stay-ticks`
  - `fade-out-ticks`
  - `command`
- Five preset v1.0 custom events:
  - `meteor-shower`
  - `gravity-well`
  - `hot-potato`
  - `jackpot-rush`
  - `blink-surge`
- Validation for custom events during plugin startup and reload.
- Full custom event reference in `CUSTOM_EVENTS.md`.
- Extra GUI event details for configured events:
  - duration
  - minimum players

### Changed

- Event registration now reloads built-in and custom events together.
- Event menu icons are resolved through the event contract so built-in and custom events share the same UI flow.
- Queue, random selection, announcements, bossbar, placeholders, and GUI chance editing now work with custom events as first-class entries.
- GUI queue input now supports middle click, `Q`, and `Ctrl+Q` for adding events to the queue.
- Shipped config presets now demonstrate titles, command actions, per-action chance, custom start/end messages, custom bossbar text, and multi-target random selection.

### Version

- Plugin version bumped from `0.0.4` to `1.0.0`.
