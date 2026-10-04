# starfield-effect

flying starfield (wheel=speed, SPACE=mode)

Category: shapes

![starfield-effect](../demos/starfield-effect.gif)

## Run it

```sh
cd starfield-effect && bb run   # from this demo (or jolt run, jolt -M:run)
bb starfield-effect             # from the repo root (or jolt -M:starfield-effect)
```

## About

raylib [shapes] example - starfield effect.

Stars fly toward the camera under a simple perspective projection: MOUSE
WHEEL changes speed, SPACE toggles streak-lines vs circles. Distinct from
stars.clj (a static per-star twinkle, not a raylib example port). Ported
from raylib's examples/shapes/shapes_starfield_effect.c.
