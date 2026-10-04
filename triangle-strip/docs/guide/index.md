# triangle-strip

a rainbow strip via rlgl immediate mode

Category: shapes

Ported from raylib's `examples/shapes/shapes_triangle_strip.c`.

![triangle-strip](../demos/triangle-strip.gif)

## Run it

```sh
cd triangle-strip && bb run   # from this demo (or jolt run, jolt -M:run)
bb triangle-strip             # from the repo root (or jolt -M:triangle-strip)
```

## About

raylib [shapes] example - triangle strip (`joltc -M:triangle-strip`).

A rainbow band across the window built vertex by vertex via rlgl immediate mode
(rlBegin RL_TRIANGLES + rlColor4ub + rlVertex2f), the scalar path around
raylib's by-value Vector2 shape APIs.
