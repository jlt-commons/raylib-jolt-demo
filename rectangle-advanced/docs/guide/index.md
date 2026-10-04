# rectangle-advanced

per-side roundness with a horizontal gradient

Category: shapes

![rectangle-advanced](../demos/rectangle-advanced.gif)

## Run it

```sh
cd rectangle-advanced && bb run   # from this demo (or jolt run, jolt -M:run)
bb rectangle-advanced             # from the repo root (or jolt -M:rectangle-advanced)
```

## About

raylib [shapes] example - rectangle advanced (`jolt -M:rectangle-advanced`).

Port of raylib's examples/shapes/shapes_rectangle_advanced.c. Five bars, each
rounded by a different amount on the left and right, each filled with a
horizontal gradient.

raylib has no call for this. The C example builds it out of rlgl by hand, for
exactly the reason the rest of this suite reaches for rlgl: DrawRectangleRounded
takes a Rectangle by value and only accepts one roundness and one colour. So
both the C and this port draw the outline vertex by vertex.

The shape is a triangle fan from the centre. Walking the outline gives the four
corner arcs, each swept over `segments/4` steps at whatever radius its own side
asked for, joined by the straight runs between them. A corner with roundness 0
collapses to a right angle because its arc radius is zero, which is why the
same loop draws a square and a lozenge without a special case.

The gradient is free once the fan exists. Colour is interpolated per vertex
from each point's own x, so the GPU fills between them and the bar shades
smoothly however many segments it has.

See rounded-rectangle for the single-roundness version, and triangle-strip for
the same immediate-mode path used more simply.
