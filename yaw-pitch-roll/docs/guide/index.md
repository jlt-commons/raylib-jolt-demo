# yaw-pitch-roll

the three aircraft rotations in 3D

Category: 3d

![yaw-pitch-roll](../demos/yaw-pitch-roll.gif)

## Run it

```sh
cd yaw-pitch-roll && bb run   # from this demo (or jolt run, jolt -M:run)
bb yaw-pitch-roll             # from the repo root (or jolt -M:yaw-pitch-roll)
```

## About

raylib [models] example - yaw, pitch and roll (`jolt -M:yaw-pitch-roll`).

A plane built out of boxes, flown with the three aircraft rotations: A/D yaw,
W/S pitch, Q/E roll. Let go and each axis eases back to level, which makes the
order the rotations compose in easy to feel: roll is applied in the plane's own
frame, so rolling first and then pitching does not put the nose where pitching
first and then rolling does.

The rotations are rlgl matrix-stack operations (push, three rotatef calls, pop)
rather than a matrix built here and multiplied in. rlgl applies whatever
transform is current to each vertex as it is submitted, so wrapping the model in
a push/pop moves the whole thing and leaves the rest of the scene alone.
