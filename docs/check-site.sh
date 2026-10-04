#!/usr/bin/env bash
# Assertions the documentation build must satisfy.
#
# Run by the shared site workflow in jlt-commons/ci-builds against the freshly
# built _site, with BASE_PATH exported. Run it locally the same way, from the
# repo root, with a docs-engine checkout next to this repo:
#   (cd ../docs-engine && jolt run build ../raylib-jolt-demo)
#   BASE_PATH=/raylib-jolt-demo bash docs/check-site.sh

set -euo pipefail
out=_site

test -f "$out/index.html"       || { echo "no homepage generated"; exit 1; }
test -f "$out/guide/index.html" || { echo "no guide page generated"; exit 1; }
test -f "$out/guide/demos.html" || { echo "no gallery page generated"; exit 1; }
test -f "$out/css/screen.css"   || { echo "static assets missing"; exit 1; }

# The gallery is the point of the site. A missing asset dir is only a warning
# inside the engine, so it has to be an error here or the site publishes with
# every image broken.
copied=$(find "$out/demos" -type f \( -name '*.gif' -o -name '*.png' \) 2>/dev/null | wc -l | tr -d ' ')
source=$(find docs/demos -type f \( -name '*.gif' -o -name '*.png' \) | wc -l | tr -d ' ')
test "$copied" = "$source" \
  || { echo "copied $copied demo recordings, expected $source"; exit 1; }

# Every image the gallery references has to exist in the build. The engine
# leaves them relative to the page (../demos/x.gif from guide/).
missing=0
for src in $(grep -oE 'src="\.\./demos/[^"]+"' "$out/guide/demos.html" | sed -E 's|src="\.\./||; s|"$||'); do
  test -f "$out/$src" || { echo "gallery image missing: $src"; missing=1; }
done
test "$missing" = 0 || exit 1
imgs=$(grep -oE 'src="\.\./demos/' "$out/guide/demos.html" | wc -l | tr -d ' ')
test "$imgs" = "$source" \
  || { echo "gallery shows $imgs images, expected $source"; exit 1; }

! grep -rq '{{site-base}}' "$out"/index.html "$out"/guide/*.html \
  || { echo "unrendered template variable"; exit 1; }

# Served at jlt-commons.github.io/raylib-jolt-demo/, a root-absolute URL loads
# the ORGANIZATION site's asset instead of this project's.
if grep -ohE '(href|src)="/[^"]*"' "$out"/index.html "$out"/404.html "$out"/guide/*.html \
     | grep -vE "=\"$BASE_PATH/"; then
  echo "the URLs above escape $BASE_PATH and would resolve against the org site"
  exit 1
fi

echo "build looks correct: $copied demo recordings, every URL under $BASE_PATH"
