# monitor-detector

every attached display, current one lit

Category: core

![monitor-detector](../demos/monitor-detector.gif)

## Run it

```sh
cd monitor-detector && bb run   # from this demo (or jolt run, jolt -M:run)
bb monitor-detector             # from the repo root (or jolt -M:monitor-detector)
```

## About

raylib [core] example - monitor detector (`jolt -M:monitor-detector`).

Every display attached to the machine, with its resolution and refresh rate,
and which one the window is currently on. Move the window to another screen and
the highlight follows it.

All four queries are scalar, so they bind directly. GetMonitorPosition is the
one that does not: it returns a Vector2 by value, which is why the layout below
is a list rather than a to-scale map of where the displays sit.
