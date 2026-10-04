# input

steer a ball with the arrow keys

Category: core

Ported from raylib's `examples/core/core_input_keys.c`.

![input](../demos/input.gif)

## Run it

```sh
cd input && bb run   # from this demo (or jolt run, jolt -M:run)
bb input             # from the repo root (or jolt -M:input)
```

## About

raylib [core] example - input keys (`joltc -M:input`).

Ported from examples/core/core_input_keys.c: move a circle with the arrow keys.
The C original uses a Vector2 + DrawCircleV; here the position is two doubles
and the ball is drawn with the scalar DrawCircle, so no by-value Vector2 crosses
the FFI boundary (only Color does).
