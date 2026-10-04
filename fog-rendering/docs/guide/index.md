# fog-rendering

exponential distance fog in the light shader

Category: shaders

![fog-rendering](../demos/fog-rendering.png)

## Run it

```sh
cd fog-rendering && bb run   # from this demo (or jolt run, jolt -M:run)
bb fog-rendering             # from the repo root (or jolt -M:fog-rendering)
```

## About

raylib [shaders] example - fog rendering (`jolt -M:fog-rendering`).

Port of raylib's examples/shaders/shaders_fog_rendering.c. The lighting
shader from net.b12n.raylib-jlt.basic-lighting with two uniforms added, so
surfaces fade toward a fog colour with distance from the eye.

The fog itself is four lines of GLSL. What makes it work is that the vertex
stage already hands the fragment stage a world-space position, which is the
same reason lighting needs a custom vertex shader at all. Distance is then
just `length(viewPos - fragPosition)`, and the falloff is exponential:
`1/exp((d*density)^2)`, which thickens fast and never quite reaches zero.

The eye position is written every frame from here. raylib does not push it:
SHADER_LOC_VECTOR_VIEW appears nowhere in its source but its own enum. Get
that wrong and the fog is measured from the origin rather than the camera,
which still fades things plausibly and is still wrong.

Upstream textures the cubes with texel_checker.png. No files ship here, so
the checker is generated and pushed into the material's diffuse map with
rl/material-diffuse-texture!. The texture matters more than decoration: on
flat colour the fog reads as a wash, and on a repeating pattern you can see
which row it has reached.

UP and DOWN change the density. At zero the far cubes come back.
