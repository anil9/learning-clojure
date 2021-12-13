(ns learning-clojure.advent-of-code.2021.day7
  (:require
    [learning-clojure.advent-of-code.2021.input-reader :as input-reader]
    [clojure.string :as str]
    [clojure.set :as sets]))
(def day "day7")
(def part1-example-file (str day "/example-input-part1"))
(def part1-file (str day "/input-part1"))
(def part2-example-file (str day "/example-input-part2"))
(def part2-file (str day "/input-part2"))

(def part1-data
  (->> (input-reader/split-line-input-at part1-file #",")
       (flatten)
       (map #(Long/parseLong %))))

(defn fuel
  ([[x y]]
   (fuel x y))
  ([x y]
   (Math/abs (- x y))))

(defn total-fuel-at [x crabs]
  (apply +
         (->> crabs
              (map #(fuel x %)))))



(defn all-paths[crabs]
  (->> (range 0 (inc (apply max crabs)))
       (map #(total-fuel-at % crabs))))

(defn part1 []
  (apply min (all-paths part1-data)))
(comment
  (total-fuel-at 2 part1-data)
  (time (all-paths part1-data))
  (time (part1)))



