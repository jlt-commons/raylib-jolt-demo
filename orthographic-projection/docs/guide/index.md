# orthographic-projection

perspective vs orthographic (SPACE toggles)

Category: 3d

Ported from raylib's `examples/core/core_3d_camera.c`.

![orthographic-projection](../demos/orthographic-projection.gif)

## Run it

```sh
cd orthographic-projection && bb run   # from this demo (or jolt run, jolt -M:run)
bb orthographic-projection             # from the repo root (or jolt -M:orthographic-projection)
```

## About

raylib [core] example - the same 3D scene under perspective vs orthographic
projection. Press SPACE to toggle with-camera-3d's :projection (0/1). For
orthographic, fovy is the view height (raylib convention). See
docs/guide/struct-by-value-pointer-trick.md.
