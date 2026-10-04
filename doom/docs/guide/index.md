# doom

a textured raycaster: one ray per screen column

Category: 3d

Ported from [babashka/ffi](https://github.com/babashka/ffi/blob/main/examples/doom.clj), MIT licensed. See NOTICE.

![doom](../demos/doom.gif)

## Run it

```sh
cd doom && bb run   # from this demo (or jolt run, jolt -M:run)
bb doom             # from the repo root (or jolt -M:doom)
```

## About

A textured raycaster (`jolt -M:doom`).

WASD or the arrow keys move, the mouse or LEFT/RIGHT turn, left click or SPACE
shoots, ESC quits.

The 2.5D rendering technique Wolfenstein and Doom made famous, and the reason
it is worth having next to `first-person-maze`: that example walks a grid of
real 3D cubes under a Camera3D, while this one casts ONE RAY PER SCREEN COLUMN
and draws each hit as a single textured vertical strip. No 3D geometry, no
camera matrix, no depth buffer — just a DDA walk over a grid of characters.

`cast-column` is the whole renderer: step cell by cell along the ray until a
solid one, and the distance you stepped decides the strip's height (closer
means taller) while the fraction of the wall you hit decides its texture u
coordinate. That distance is also kept per column in `zbuf`, which is what the
sprite pass depth-tests against: each imp is cut into vertical strips, and a
strip is drawn only where it is nearer than the wall column behind it. That is
how a sprite gets occluded by geometry without a per-pixel depth buffer.

Everything visible goes through the shared layer. The texture atlas — four
wall styles and one sprite, 64x64 each, stacked vertically — is
`rl/texture-from-fn`, generated procedurally so the example ships no assets,
and the strips are rlgl quads wound the way `rl/texture!` winds its own.
