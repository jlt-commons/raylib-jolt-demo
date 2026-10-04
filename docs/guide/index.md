# raylib-jolt-demo

187 [raylib](https://github.com/raysan5/raylib) examples written in
[Jolt](https://github.com/jolt-lang/jolt), native Clojure on Chez Scheme. Every
example is its own small project with a `deps.edn`, a `bb.edn` and one source
file, so you can read one, copy one out or run one without the other 186
getting in the way. They all call raylib through the bindings in
[raylib-jlt](https://github.com/jlt-commons/raylib-jlt), which talk to the
system libraylib directly over its C ABI. jolt fetches those bindings itself,
as a git dependency pinned in `common/deps.edn`.

The [gallery](demos.md) shows every demo with its recording.

## Getting it running

You need jolt, raylib 6.0+ and, optionally, babashka (jolt runs the same
tasks):

```sh
brew install raylib            # or your distro's raylib package
git clone https://github.com/jlt-commons/raylib-jolt-demo
cd raylib-jolt-demo
bb doctor                      # checks all of the above
```

Then pick a demo:

```sh
bb asteroids                   # from the repo root, or: jolt asteroids
cd asteroids && bb run         # from inside the demo, or: jolt run
```

`bb info` prints every task and every demo, grouped by category. `bb check`
compiles all 187 without opening a window, and `bb run-all 2` runs each one
for two seconds.

## How a demo is laid out

```
asteroids/
  deps.edn                       depends on ../common
  bb.edn                         the `run` task
  src/net/b12n/raylib_jlt/asteroids.clj
  docs/guide/index.md            what it shows, where it was ported from
  docs/demos/asteroids.gif       its recording
```

`common/` holds the two files several demos share, the headless smoke-test
harness and an easing-function library, and pins the raylib-jlt version every
demo uses.

## Where the examples came from

They were split out of raylib-jlt, which now holds only the bindings. Most are
ports of raylib's own example programs, and each demo's page says which one. Three
(`helitorus`, `doom` and `pacman`) are ports of Michiel Borkent's
[babashka/ffi](https://github.com/babashka/ffi) examples. The rest were
written for raylib-jlt. The
[NOTICE](https://github.com/jlt-commons/raylib-jolt-demo/blob/main/NOTICE)
file carries the licence terms of each.

For how the bindings work (structs by value, the rlgl workarounds, headless
smoke testing) see the
[raylib-jlt guide](https://jlt-commons.github.io/raylib-jlt/).
