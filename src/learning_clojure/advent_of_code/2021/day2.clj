(ns learning-clojure.advent-of-code.2021.day2
  (:require [learning-clojure.advent-of-code.2021.input-reader :as input-reader]))

(def day "day2")
(def part1-example-file (str day "/example-input-part1"))
(def part1-file (str day "/input-part1"))
(def part2-example-file (str day "/example-input-part2"))
(def part2-file (str day "/input-part2"))


(defn horizontal-position [coll]
  (apply + (->> coll
                (filter #(= "forward" (first %)))
                (map second)
                (map #(Integer/parseInt %)))))

(defn get-vertical-value [[direction value]]
  (if (= "down" direction)
    (Integer/parseInt value)
    (unchecked-negate-int (Integer/parseInt value))))


(defn vertical-position [coll]
  (apply + (->> coll
                (filter #(or (= "up" (first %)) (= "down" (first %))))
                (map get-vertical-value))))

(def part1-data
  (input-reader/split-line-input-at part1-file " "))

(defn part1 []
  (let [vertical (vertical-position part1-data)
        horizontal (horizontal-position part1-data)]
    (* vertical horizontal)))
