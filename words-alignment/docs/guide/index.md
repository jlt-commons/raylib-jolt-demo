# words-alignment

align a word inside a box (MeasureText)

Category: text

Ported from raylib's `examples/text/text_words_alignment.c`.

![words-alignment](../demos/words-alignment.gif)

## Run it

```sh
cd words-alignment && bb run   # from this demo (or jolt run, jolt -M:run)
bb words-alignment             # from the repo root (or jolt -M:words-alignment)
```

## About

raylib [text] example - word alignment (`joltc -M:words-alignment`).

A word aligned left / centre / right inside a box using MeasureText to compute
the horizontal offset. The alignment cycles over time.
