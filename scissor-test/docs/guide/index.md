# scissor-test

a scissor rectangle clips a grid

Category: core

Ported from raylib's `examples/core/core_scissor_test.c`.

![scissor-test](../demos/scissor-test.gif)

## Run it

```sh
cd scissor-test && bb run   # from this demo (or jolt run, jolt -M:run)
bb scissor-test             # from the repo root (or jolt -M:scissor-test)
```

## About

raylib [core] example - scissor test (`joltc -M:scissor-test`).

A colorful grid is drawn across the whole window, but a scissor rectangle clips
drawing so only the part inside the box is visible. Uses the scalar
BeginScissorMode / EndScissorMode.
