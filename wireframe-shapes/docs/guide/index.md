# wireframe-shapes

pyramid/octahedron/torus/helix in 3D lines

Category: 3d

![wireframe-shapes](../demos/wireframe-shapes.gif)

## Run it

```sh
cd wireframe-shapes && bb run   # from this demo (or jolt run, jolt -M:run)
bb wireframe-shapes             # from the repo root (or jolt -M:wireframe-shapes)
```

## About

Wireframe shapes (`joltc -M:wireframe-shapes`).

Four wireframe solids, a pyramid, an octahedron, a torus and a helix, each
tumbling under a 3D camera. Each shape is a list of 3D edges drawn with rlgl
immediate mode in RL_LINES mode (rl-vertex-3f pairs); rotation/position come from
the rlgl matrix stack, the same 3D path as camera-3d and rlgl-solar-system.
