# raylib-jolt-demo

187 [raylib](https://github.com/raysan5/raylib) examples written in
[Jolt](https://github.com/jolt-lang/jolt), each one its own small project.
They started life in [raylib-jlt](https://github.com/jlt-commons/raylib-jlt),
which now holds only the bindings. The demos pull those in as a git dependency
pinned to one commit, so all you install is jolt and raylib 6.0+
(`brew install raylib` on macOS).

The gallery of every demo is at
<https://jlt-commons.github.io/raylib-jolt-demo/>.

## Layout

```
deps.edn                      root: one alias per demo, plus :check
bb.edn                        root tasks: info, list, run, run-all, check, lint,
                              doctor, gen, plus one task per demo
demos.edn                     every demo's name, alias, category and description
src/net/b12n/raylib_jolt_demo/check.clj
common/                       shared by every demo
  deps.edn                    pins raylib-jlt by :git/sha, the only place it's named
  src/net/b12n/raylib_jlt/app.clj        headless smoke harness
  src/net/b12n/raylib_jlt/reasings.clj   easing functions (easings-* demos)
<demo>/                       187 of these, e.g. asteroids/
  deps.edn                    depends on ../common
  bb.edn                      a `run` task
  src/net/b12n/raylib_jlt/<demo>.clj
  docs/guide/index.md         what it shows, how to run it, where it was ported from
  docs/demos/<demo>.gif       its recording (.png for the 16 stills)
docs/                         the site: site.edn, guide/index.md, the generated
                              guide/demos.md gallery, and a second copy of every
                              recording in demos/, since docs-engine only
                              publishes assets from under docs/
scripts/gen.clj               rebuilds the files that list every demo
.clj-kondo/                   lint config, and the hook that reads jolt.ffi/defcfn
.github/workflows/            ci.yml (doctor, gen --check, lint, compile), site.yml (Pages)
```

The namespaces kept their raylib-jlt names (`net.b12n.raylib-jlt.asteroids`
and so on).

## Running

Every task works under both `bb` and `jolt`, because jolt reads `bb.edn` too.
Start with `bb doctor` in a fresh checkout. It checks for jolt, fetches the
pinned raylib-jlt, and looks for an installed libraylib.

From inside a demo:

```sh
cd asteroids
bb run            # or: jolt run, jolt -M:run
```

From the root, every demo has a task of its own:

```sh
bb asteroids      # or: jolt asteroids
bb run asteroids  # the same, and raylib-jlt's old names work: bb run bouncing-ball
bb info           # grouped cheat-sheet, every demo included
bb list           # flat list with descriptions
bb run-all 2      # every demo for 2 seconds each, exits 1 if any fail
bb check          # compile every demo, no window
bb lint           # clj-kondo over every demo (lint:strict fails on findings)
```

The plain aliases work too. Each demo kept the alias it had in raylib-jlt, so
it's `jolt -M:asteroids`, and the basic window example (`core/`) is still
`jolt -M:run`.

`RAYLIB_APP_AUTO_QUIT_MS=1500` closes a demo's window on a timer and
`RAYLIB_APP_SHOT=shot.png` saves one frame, which is how `run-all` and CI get
by with nobody at the keyboard.

## Moving to a newer raylib-jlt

The bindings are pinned in exactly one place, `common/deps.edn`. Every demo
depends on `common/`, so they all follow it. Change its `:git/sha` to the
raylib-jlt commit you want, then run `bb check` and `bb run-all 1`.

## Adding a demo

1. Create `<demo>/src/net/b12n/raylib_jlt/<demo>.clj`, with the namespace
   `net.b12n.raylib-jlt.<demo>` and a `-main`.
2. Copy `deps.edn` and `bb.edn` from any existing demo and change the names in
   them.
3. Add a line for it to `demos.edn`.
4. Run `bb gen`. It adds the root alias, the `:check` require, the root task and
   the gallery entry.
5. Write its `docs/guide/index.md`, and put its recording in both
   `<demo>/docs/demos/` and `docs/demos/`.

CI runs `bb gen --check`, which fails when a demo directory and `demos.edn`
disagree or when step 4 was skipped.

## CI and the site

`ci.yml` runs `bb doctor`, `bb gen --check`, `bb lint:strict` and `bb check`
on every push and pull request. `site.yml` builds the docs with
[docs-engine](https://github.com/jlt-commons/docs-engine) through the shared
workflow in jlt-commons/ci-builds, runs `docs/check-site.sh` against the
result, and deploys to GitHub Pages from main. To build it locally with a
docs-engine checkout next to this repo:

```sh
(cd ../docs-engine && jolt run build ../raylib-jolt-demo)
BASE_PATH=/raylib-jolt-demo bash docs/check-site.sh
```

The guide pages on how the bindings work (structs by value, rlgl, headless
smoke testing and the rest) aren't about any single demo, so they live in the
[raylib-jlt guide](https://jlt-commons.github.io/raylib-jlt/). A demo's page
links to the ones that discuss it.

## License

EPL 2.0, the same as raylib-jlt. See `LICENSE`. Many demos are ports of
raylib's examples (zlib) and three of babashka/ffi's (MIT). `NOTICE` carries
those terms, and each demo's page names what it was ported from.
