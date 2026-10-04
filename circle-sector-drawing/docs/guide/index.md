# circle-sector-drawing

a sector whose segment count you can starve

Category: shapes

![circle-sector-drawing](../demos/circle-sector-drawing.gif)

## Run it

```sh
cd circle-sector-drawing && bb run   # from this demo (or jolt run, jolt -M:run)
bb circle-sector-drawing             # from the repo root (or jolt -M:circle-sector-drawing)
```

## About

raylib [shapes] example - circle sector drawing (`jolt -M:circle-sector-drawing`).

Port of raylib's examples/shapes/shapes_circle_sector_drawing.c. One sector,
with its start angle, end angle, radius and segment count all adjustable, so
you can watch a pie slice degrade into a triangle as the segments drop.

The lesson is the segment count. raylib needs at least one segment per 90
degrees of arc to produce something that still reads as a curve, and it
computes that floor itself when given fewer: `ceil((end - start)/90)`. Below
the floor the drawing is AUTO, at or above it the count you asked for is used.
The readout says which is in force.

The C drives all four values with raygui sliders. raygui is a separate library
and is not bound here, so the controls are keys instead: Q/A and W/S for the two
angles, E/D for radius, R/F for segments. Nothing else about the example
changes, and the arithmetic being demonstrated is untouched.

See pie-chart for sectors used for something, and ring-drawing for the annulus
built the same way.
