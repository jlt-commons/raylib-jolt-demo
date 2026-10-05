(ns sync-recordings
  "Keep each demo's own recording in step with the site's copy
  (`bb sync-recordings`, and the tail end of `bb record`).

  screen-grab writes into docs/demos/, which is what the site publishes. Each
  demo also carries its recording in <demo>/docs/demos/, so the sub-project
  stands alone. For every demo in demos.edn this:

  - copies docs/demos/<demo>.gif (or .png) into <demo>/docs/demos/ when the two
    differ or the demo's copy is missing
  - when a demo now has a .gif where it used to have only a .png still, points
    <demo>/docs/guide/index.md at the .gif and deletes both .png copies

  Usage: bb sync-recordings [--check]
  --check changes nothing and exits 1 if any demo's copy is out of step."
  (:require
   [babashka.fs :as fs]
   [clojure.edn :as edn]
   [clojure.string :as str]))

(def root (str (fs/parent (fs/parent (fs/absolutize *file*)))))

(defn- same-bytes? [a b]
  (and (fs/exists? a) (fs/exists? b)
       (java.util.Arrays/equals (fs/read-all-bytes a) (fs/read-all-bytes b))))

(defn- plan
  "The changes one demo needs, as [[:copy from to] [:delete path] [:relink page]]."
  [name]
  (let [site-gif (fs/file root "docs/demos" (str name ".gif"))
        site-png (fs/file root "docs/demos" (str name ".png"))
        own-dir (fs/file root name "docs/demos")
        own-png (fs/file own-dir (str name ".png"))
        page (fs/file root name "docs/guide/index.md")
        src (cond (fs/exists? site-gif) site-gif
                  (fs/exists? site-png) site-png)]
    (concat
     (when (and src (not (same-bytes? src (fs/file own-dir (fs/file-name src)))))
       [[:copy src (fs/file own-dir (fs/file-name src))]])
     ;; A recorded gif supersedes a still: drop both pngs and relink the page.
     (when (fs/exists? site-gif)
       (concat (when (fs/exists? site-png) [[:delete site-png]])
               (when (fs/exists? own-png) [[:delete own-png]])
               (when (and (fs/exists? page)
                          (str/includes? (slurp page) (str "](../demos/" name ".png)")))
                 [[:relink page name]]))))))

(defn- apply! [[op a b]]
  (case op
    :copy (do (fs/create-dirs (fs/parent b))
              (fs/copy a b {:replace-existing true}))
    :delete (fs/delete a)
    :relink (spit a (str/replace (slurp a) (str "](../demos/" b ".png)") (str "](../demos/" b ".gif)")))))

(defn- describe [[op a]]
  (str (name op) " " (str (fs/relativize root a))))

(defn -main [& args]
  (let [check? (some #{"--check"} args)
        demos (edn/read-string (slurp (fs/file root "demos.edn")))
        steps (mapcat (comp plan :name) demos)]
    (doseq [step steps]
      (println (if check? "out of step:" "") (describe step))
      (when-not check? (apply! step)))
    (println (str (count demos) " demos, " (count steps) " change(s) "
                  (if check? "needed" "made")))
    (when (and check? (seq steps))
      (binding [*out* *err*] (println "Run `bb sync-recordings` and commit the result."))
      (System/exit 1))))

(when (= *file* (System/getProperty "babashka.file"))
  (apply -main *command-line-args*))
