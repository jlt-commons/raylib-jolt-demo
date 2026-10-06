# to-image

one image, VRAM to RAM to VRAM and back up

Category: textures

![to-image](../demos/to-image.gif)

## Run it

```sh
cd to-image && bb run   # from this demo (or jolt run, jolt -M:run)
bb to-image             # from the repo root (or jolt -M:to-image)
```

## About

raylib [textures] example - to image (`jolt -M:to-image`).

Port of raylib's examples/textures/textures_to_image.c. The C walks one
picture around the loop RAM to VRAM to RAM to VRAM and draws the result,
which looks exactly like the picture it started with. That is the point,
and it is also why the C on its own shows you nothing: a correct round
trip and a no-op are indistinguishable on screen.

So this port makes the middle of the trip visible. Both panels start from
the same generated image. The left one is uploaded once and never comes
back down. The right one is uploaded, pulled off the GPU with
LoadImageFromTexture, stamped on the CPU with ImageDrawText while it sits
in RAM, and uploaded again. The stamp is the evidence: those pixels can
only have been written by a CPU-side draw into a real buffer, so if it
shows up, the read-back genuinely returned the image rather than a handle.

No image files ship in this suite, so the source is GenImageGradientRadial
with a few shapes baked in, the same way net.b12n.raylib-jlt.image-drawing
builds its source.
