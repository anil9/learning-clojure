(ns learning-clojure.codewars.strip-comments
  (:require
   [clojure.string :as s]))

;; https://www.codewars.com/kata/51c8e37cee245da6b40000bd/train/clojure

(defn min-0
  ([] nil)
  ([x & args] (apply min x args)))

(defn strip-comment [line symbols]
  (let [symbol-index (->> symbols
                          (map #(s/index-of line %))
                          (filter some?)
                          (reduce min-0))]
    (if (nil? symbol-index)
      line
      (subs line 0 symbol-index))))

(defn strip-comments [text comment-symbols]
  (s/join "\n" (->> (s/split-lines text)
                    (map #(strip-comment % comment-symbols))
                    (map s/trimr))))
(def example "apples, pears # and bananas\ngrapes\nbananas !apples")
(def symbols '("!" "#"))
(def sentence (last (s/split-lines example)))

(comment
  (strip-comment sentence symbols))
