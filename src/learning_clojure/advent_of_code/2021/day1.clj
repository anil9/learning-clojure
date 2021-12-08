(ns learning-clojure.advent-of-code.2021.day1
  (:require [clojure.string :as str]))
(def part1-example-file "/home/andreas/temp/advent-of-code/day1/example-input-part1")
(def part1-file "/home/andreas/temp/advent-of-code/day1/input-part1")
(def part2-example-file "/home/andreas/temp/advent-of-code/day1/example-input-part2")
(def part2-file "/home/andreas/temp/advent-of-code/day1/input-part2")

(defn get-int-input [x]
  (->> (str/split-lines (slurp x))
       (map #(Integer/parseInt %))))

(defn count-increases-between-elements [coll]
  (count (->> (partition 2 1 coll)
              (map #(if (< (first %) (second %)) "increased" "decreased"))
              (filter #(= "increased" %)))))
(defn part1 []
    (count-increases-between-elements (get-int-input part1-file)))

(defn count-3-sliding-window-increase-sum [coll]
  (count-increases-between-elements
    (->> (partition 3 1 coll)
         (map #(apply + %)))))

(defn part2 []
    (count-3-sliding-window-increase-sum (get-int-input part2-file)))


