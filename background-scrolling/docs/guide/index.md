# background-scrolling

three parallax skyline layers, each scrolling

Category: textures

![background-scrolling](../demos/background-scrolling.gif)

## Run it

```sh
cd background-scrolling && bb run   # from this demo (or jolt run, jolt -M:run)
bb background-scrolling             # from the repo root (or jolt -M:background-scrolling)
```

## About

raylib [textures] example - background scrolling.

Parallax scrolling: three procedurally generated skyline layers
(background, midground, foreground) scroll left at different speeds,
each drawn twice at 2x scale so the seam wraps.

No new FFI: each layer is rl/texture-from-fn (a silhouette skyline,
same technique blend-modes.clj's sky-pixel uses, one hash seed per
layer so the three don't repeat the same shape), and the 2x scale is
just rl/texture!'s own :width/:height, since raylib's DrawTextureEx
scale factor is nothing more than that.
Loosely based on raylib/examples/textures/textures_background_scrolling.c.
