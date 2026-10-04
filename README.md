# raylib-jolt-demo

The raylib example suite from
[raylib-jlt](https://github.com/jlt-commons/raylib-jlt), split so each example
is its own small jolt project. Every demo talks to raylib through the bindings
in `../raylib-jlt/lib`, so that checkout has to sit next to this one, and
raylib 6.0+ has to be installed (`brew install raylib` on macOS).

## Layout

```
deps.edn                      root: one alias per demo, plus :check
src/net/b12n/raylib_jolt_demo/check.clj
common/                       shared by every demo
  src/net/b12n/raylib_jlt/app.clj        headless smoke harness
  src/net/b12n/raylib_jlt/reasings.clj   easing functions (easings-* demos)
<demo>/                       187 of these, e.g. asteroids/
  deps.edn                    depends on ../../raylib-jlt/lib and ../common
  src/net/b12n/raylib_jlt/<demo>.clj
  docs/guide/index.md         what it shows, how to run it, its ns docstring
  docs/demos/<demo>.gif       its recording (.png for the 16 stills)
scripts/resync.clj            re-copies all of the above from raylib-jlt
```

The namespaces are unchanged from raylib-jlt (`net.b12n.raylib-jlt.asteroids`
and so on), so a file here and its original diff clean.

## Running

From inside a demo:

```sh
cd asteroids
jolt -M:run
```

From the root, each demo runs under the alias it had in raylib-jlt. The basic
window example (`core/`) is still `:run`.

```sh
jolt -M:asteroids
jolt -M:run
jolt -M:check     # compile every demo, no window
```

The smoke-test variables from raylib-jlt still work.
`RAYLIB_APP_AUTO_QUIT_MS=1500` closes the window on a timer and
`RAYLIB_APP_SHOT=shot.png` saves one frame.

## Re-syncing from raylib-jlt

Until the migration is finished, raylib-jlt is still where the examples
change, so pull those changes across with:

```sh
bb resync --dry-run      # list what would change
bb resync                # default source is ../raylib-jlt
bb resync ~/elsewhere/raylib-jlt
```

It overwrites each example's source, `common/`, and every
`<demo>/docs/guide/index.md` and recording. A demo's `deps.edn` is only
written when it's missing, so edits to it survive. The root `deps.edn` and
`check.clj` are rebuilt from the demo directories present here. That means a
demo added by hand keeps its alias even though raylib-jlt has never heard of
it. The script never deletes anything, so a demo removed upstream has to be
removed here by hand.

The general guide pages (structs by value, rlgl, headless smoke testing and
the rest) aren't about any single demo, so they stay in the
[raylib-jlt guide](https://github.com/jlt-commons/raylib-jlt/tree/main/docs/guide).
The per-demo pages link to the ones that discuss them.

## Adding a demo

Create `<demo>/deps.edn` and `<demo>/src/net/b12n/raylib_jlt/<demo>.clj`,
copying any existing demo's `deps.edn` and changing the namespace in `:run`.
Then run `bb resync` to add its root alias and its `:check` entry. Write its
`docs/guide/index.md` by hand, since resync only generates pages for demos
that come from raylib-jlt.
