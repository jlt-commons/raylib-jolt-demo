# camera-3d-first-person

walk a yard of columns in first person

Category: 3d

Ported from raylib's `examples/core/core_3d_camera_first_person.c`.

![camera-3d-first-person](../demos/camera-3d-first-person.gif)

## Run it

```sh
cd camera-3d-first-person && bb run   # from this demo (or jolt run, jolt -M:run)
bb camera-3d-first-person             # from the repo root (or jolt -M:camera-3d-first-person)
```

## About

raylib [core] example - first-person 3D camera (`joltc -M:camera-3d-first-person`).

Walk a yard of random columns in first person: WASD moves, the mouse looks. Same
3D path as camera-3d (Camera3D by value + rlgl cube!). The look direction is
built from yaw/pitch and handed to the camera as its target; mouse-look uses
GetMouseX/GetMouseY deltas over a free cursor, so turning is bounded by the
window edges (a proper lock would need GetMouseDelta, which returns a by-value
Vector2).
