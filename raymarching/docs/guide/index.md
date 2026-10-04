# raymarching

a raymarched SDF scene in a shader

Category: shaders

![raymarching](../demos/raymarching.gif)

## Run it

```sh
cd raymarching && bb run   # from this demo (or jolt run, jolt -M:run)
bb raymarching             # from the repo root (or jolt -M:raymarching)
```

## About

raylib [shaders] example - raymarching (`jolt -M:raymarching`).

A 3D scene with no geometry at all. Every pixel walks a ray forward until it is
close enough to a surface, where "close enough" comes from a signed distance
function: a function that returns how far the nearest surface is from any point
in space. Take that many steps and you cannot overshoot, so the walk converges
on the surface without ever intersecting a triangle.

The scene is a plane, a sphere, a box and a torus, combined with min() for union
and a smooth minimum for the blend between the sphere and the box. Normals come
from sampling the distance field either side of the hit point, which is why
lighting works without a single vertex normal being stored.

W/S/A/D fly the camera, the mouse looks around, SPACE toggles a heat map of the
step count - the bright regions are where rays travel nearly parallel to a
surface and the marcher has to creep.

Contrast the 3D examples elsewhere in this suite: those push vertices through
rlgl. Here the GPU is handed four numbers and derives the whole image.
