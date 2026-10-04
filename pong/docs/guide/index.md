# pong

two-paddle classic, you (W/S) vs a CPU

Category: games

![pong](../demos/pong.gif)

## Run it

```sh
cd pong && bb run   # from this demo (or jolt run, jolt -M:run)
bb pong             # from the repo root (or jolt -M:pong)
```

## About

Pong (`joltc -M:pong`).

The classic, ported to jolt: your paddle is on the left (W / S); the right paddle
is a CPU that tracks the ball. The ball speeds up nothing fancy. It just takes
english off where it hits a paddle. First to 7 wins; ENTER restarts.

One immutable state map threaded through the loop; `step` reads input and returns
the next state.
