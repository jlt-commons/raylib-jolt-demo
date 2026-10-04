# collision-area

AABB collision between two boxes

Category: shapes

Ported from raylib's `examples/shapes/shapes_collision_area.c`.

![collision-area](../demos/collision-area.gif)

## Run it

```sh
cd collision-area && bb run   # from this demo (or jolt run, jolt -M:run)
bb collision-area             # from the repo root (or jolt -M:collision-area)
```

## About

raylib [shapes] example - collision area (`joltc -M:collision-area`).

A blue box bounces horizontally; a gold box follows the mouse. When they
overlap, the intersection rectangle is highlighted in red. AABB overlap is
computed in Clojure, so no by-value Rectangle crosses the FFI boundary.
