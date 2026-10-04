# camera2d

a 2D camera over a skyline (struct-by-value)

Category: core

Ported from raylib's `examples/core/core_2d_camera.c`.

![camera2d](../demos/camera2d.gif)

## Run it

```sh
cd camera2d && bb run   # from this demo (or jolt run, jolt -M:run)
bb camera2d             # from the repo root (or jolt -M:camera2d)
```

## About

raylib [core] example - 2D camera (`joltc -M:camera2d`).

Ported from examples/core/core_2d_camera.c: a row of buildings with a player
box; the camera follows the player (arrow keys move), the mouse wheel zooms,
A/D rotate, and R resets.

This is the project's one struct-by-value example. raylib's BeginMode2D takes a
24-byte `Camera2D` BY VALUE; net.b12n.raylib.camera/with-camera-2d builds that struct in
native memory and passes a pointer (the AArch64 ABI for a >16-byte struct, see
the note in net.b12n.raylib.camera and README.md).

Verified: the struct-by-value pointer approach renders correctly on AArch64
(Apple silicon). If you ever hit an invalid-memory crash on another platform
(e.g. x86-64, where a >16-byte struct is passed on the stack, not by pointer),
fall back to applying the transform with rlgl's scalar matrix ops (rlPushMatrix
/ rlTranslatef / rlRotatef / rlScalef, flushing the batch before rlPopMatrix),
which is what BeginMode2D does internally.
