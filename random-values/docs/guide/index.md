# random-values

a new random value every two seconds

Category: core

Ported from raylib's `examples/core/core_random_values.c`.

![random-values](../demos/random-values.gif)

## Run it

```sh
cd random-values && bb run   # from this demo (or jolt run, jolt -M:run)
bb random-values             # from the repo root (or jolt -M:random-values)
```

## About

raylib [core] example - random values (`joltc -M:random-values`).

A new random value (0-99) every two seconds via GetRandomValue, with a small
history of recent rolls.
