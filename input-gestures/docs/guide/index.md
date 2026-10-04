# input-gestures

tap, hold, drag and swipe, named as they happen

Category: core

![input-gestures](../demos/input-gestures.gif)

## Run it

```sh
cd input-gestures && bb run   # from this demo (or jolt run, jolt -M:run)
bb input-gestures             # from the repo root (or jolt -M:input-gestures)
```

## About

raylib [core] example - input gestures (`jolt -M:input-gestures`).

Port of raylib's examples/core/core_input_gestures.c. Gestures made inside the
right-hand box are named and pushed onto a log down the left. On a desktop the
mouse stands in for a finger, so tap, double-tap, hold and drag all work; the
swipes and pinches need a trackpad or a touchscreen.

raylib reports one gesture at a time from GetGestureDetected, and reports it on
every frame the gesture persists. Logging that directly would fill the list
with one entry per frame, so an entry is recorded only when the gesture differs
from the previous frame's, which is what the C does with lastGesture.

See input-multitouch for the raw touch points underneath these.
