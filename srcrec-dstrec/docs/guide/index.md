# srcrec-dstrec

srcrec picks the frame, dstrec scales+spins it

Category: textures

![srcrec-dstrec](../demos/srcrec-dstrec.gif)

## Run it

```sh
cd srcrec-dstrec && bb run   # from this demo (or jolt run, jolt -M:run)
bb srcrec-dstrec             # from the repo root (or jolt -M:srcrec-dstrec)
```

## About

raylib [textures] example - srcrec dstrec.

One frame of a procedurally generated sprite sheet drawn like
DrawTexturePro: a source rectangle picks the frame, a destination
rectangle scales it 2x at screen center, and an origin offset makes it
spin in place (rotation increments every frame). Q quits.

No binding for DrawTexturePro exists (Rectangle/Vector2 by-value args,
see net.b12n.raylib.textures), so this reimplements it directly: rotated-quad-corners
mirrors raylib's own rtextures.c algorithm (rotate the four destination
corners around the origin offset, before translating to screen
position), and the quad is emitted through the same low-level rlgl
calls rl/texture! and polygon-drawing.clj already use
(rlSetTexture/rlBegin/rlTexCoord2f/rlVertex2f) -- no new FFI. The sheet
is six colored, ringed frames built with rl/texture-from-fn rather than
the C example's scarfy.png, matching this suite's no-external-assets
convention.
Loosely based on raylib/examples/textures/textures_srcrec_dstrec.c.
