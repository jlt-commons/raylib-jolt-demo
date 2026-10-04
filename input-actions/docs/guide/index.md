# input-actions

abstract input actions: keyboard + gamepad

Category: core

![input-actions](../demos/input-actions.gif)

## Run it

```sh
cd input-actions && bb run   # from this demo (or jolt run, jolt -M:run)
bb input-actions             # from the repo root (or jolt -M:input-actions)
```

## About

raylib [core] example - input actions.

Decodes input as abstract ACTIONS instead of raw keys, so a binding maps
to a key AND a gamepad button at once and can be remapped freely. Move the
square with the movement action (WASD by default), SPACE fires (recentres
+ flashes blue for one frame), TAB swaps to the arrow-key set. No new
bindings: the d-pad is already PAD-UP/DOWN/LEFT/RIGHT and the face buttons
are already PAD-Y/A/X/B (raylib's LEFT_FACE_* / RIGHT_FACE_* under
different names, added for input-gamepad.clj), plus one small addition,
gamepad-released?, mirroring gamepad-down?/gamepad-pressed? exactly.
Ported from raylib's examples/core/core_input_actions.c.
