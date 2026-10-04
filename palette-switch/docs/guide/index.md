# palette-switch

bands recolored by an ivec3 palette

Category: shaders

![palette-switch](../demos/palette-switch.gif)

## Run it

```sh
cd palette-switch && bb run   # from this demo (or jolt run, jolt -M:run)
bb palette-switch             # from the repo root (or jolt -M:palette-switch)
```

## About

raylib [shaders] example - palette switching (`jolt -M:palette-switch`).

The image is indices, not colours. A plasma function gives every pixel a number
0-7, and an `ivec3 palette[8]` uniform turns that number into a colour. Changing
the whole picture is uploading 24 ints; the pattern itself is never recomputed
and never re-uploaded.

This is how paletted hardware worked, and the reason palette animation was free
on machines that could not afford to touch a framebuffer twice: cycling the
entries scrolls colour through a static image. LEFT/RIGHT switch palettes,
SPACE animates by rotating the entries, and the strip along the bottom shows the
eight entries currently in force.

It is also the one example here that sends an ARRAY uniform:
rl/set-uniform-ivec3-array! stages 8 x 3 ints and hands them to SetShaderValueV
with a count, where the other setters send a single value.
