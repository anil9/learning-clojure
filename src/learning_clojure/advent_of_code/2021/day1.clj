(ns learning-clojure.advent-of-code.2021.day1
  (:require [clojure.string :as str]
            [learning-clojure.advent-of-code.2021.input-reader :as input-reader]))

(def day "day1")
(def part1-example-file (str day "/example-input-part1"))
(def part1-file (str day "/input-part1"))
(def part2-example-file (str day "/example-input-part2"))
(def part2-file (str day "/input-part2"))

;; Part 1
(defn count-increases-between-elements [coll]
  (count (->> (partition 2 1 coll)
              (map #(< (first %) (second %)))
              (filter true?))))
(defn part1 []
    (count-increases-between-elements (input-reader/get-int-input part1-file)))

;; Part 2
(defn count-3-sliding-window-increase-sum [coll]
  (count-increases-between-elements
    (->> (partition 3 1 coll)
         (map #(apply + %)))))

(defn part2 []
    (count-3-sliding-window-increase-sum (input-reader/get-int-input part2-file)))


