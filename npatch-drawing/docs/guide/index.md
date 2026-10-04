# npatch-drawing

nine-patch stretching, corners held fixed

Category: textures

![npatch-drawing](../demos/npatch-drawing.gif)

## Run it

```sh
cd npatch-drawing && bb run   # from this demo (or jolt run, jolt -M:run)
bb npatch-drawing             # from the repo root (or jolt -M:npatch-drawing)
```

## About

raylib [textures] example - npatch drawing (`jolt -M:npatch-drawing`).

Port of raylib's examples/textures/textures_npatch_drawing.c. Three panels
stretched by the mouse: a nine-patch that grows in both axes, and two
three-patches that grow in one. Move the pointer and watch the corners stay
exactly the size they were drawn at while the edges and the middle take up the
slack.

Zero new FFI, and hand-rolling it is the point. The C calls
`DrawTextureNPatch` with an `NPatchInfo` struct; `npatch!` here is that call
written out, nine `texture!` quads whose source rectangles carve the image into
a 3x3 and whose destination rectangles put the corners back at their original
size. Once it is spelled out, the two three-patch modes stop being separate
modes at all: a horizontal one is the same routine with no top or bottom
border, a vertical one with no left or right.

The C loads `resources/ninepatch_button.png`. This suite ships no image files,
so the source is drawn with `texture-from-fn`, with a deliberately busy border
and a plain middle, which is what makes a stretched patch legible: if the
corners smeared you would see it immediately.
