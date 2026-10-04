# outlines-thickness

thick outlines, and what a negative one does

Category: shapes

![outlines-thickness](../demos/outlines-thickness.png)

## Run it

```sh
cd outlines-thickness && bb run   # from this demo (or jolt run, jolt -M:run)
bb outlines-thickness             # from the repo root (or jolt -M:outlines-thickness)
```

## About

raylib [shapes] example - outlines thickness (`jolt -M:outlines-thickness`).

Port of raylib's examples/shapes/shapes_outlines_thickness.c. Three outline
calls that take a thickness, side by side, with one number driving all of
them so the shapes can be compared at the same setting.

The number goes negative, which is the part worth watching, though not for
the reason the range suggests. raylib guards both rectangle calls with
`if (thick > 0)`, so a negative thickness draws nothing at all for the plain
rectangle and collapses the rounded one to a hairline. There is no outward
band. ring! is built from two radii rather than a thickness, so it is the
only one of the three that can grow outward, and it does.

Upstream drives the value with a raygui slider. raygui is a separate library
and this suite does not bind it, so the value sweeps on its own here and UP
and DOWN take over once either is pressed.

Two of these needed new bindings. DrawRectangleLinesEx,
DrawRectangleRounded and DrawRectangleRoundedLinesEx take their Rectangle by
value, which jolt could not do before 0.7.23. The circle is ring!, because
DrawCircleLinesEx landed after the 6.0 tag and the released library this
suite links does not export it at all. net.b12n.raylib-jlt.rounded-rectangle hand-rolls its corners out of
sectors for exactly that reason and is left alone: the rlgl paths in this
suite are not being migrated opportunistically.
