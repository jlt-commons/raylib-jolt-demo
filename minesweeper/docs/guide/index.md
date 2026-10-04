# minesweeper

reveal/flag grid (mouse L reveal, R flag)

Category: games

![minesweeper](../demos/minesweeper.gif)

## Run it

```sh
cd minesweeper && bb run   # from this demo (or jolt run, jolt -M:run)
bb minesweeper             # from the repo root (or jolt -M:minesweeper)
```

## About

raylib [games] example - minesweeper. Left-click reveals (0-cells flood-fill),
right-click flags; find every safe cell without hitting a mine (SPACE restarts).
Exercises the mouse-pressed? / MOUSE-RIGHT toolkit binds.
