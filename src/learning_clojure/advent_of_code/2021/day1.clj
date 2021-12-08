(ns learning-clojure.advent-of-code.2021.day1
  (:require [clojure.string :as str]))
(def part1-file "/home/andreas/temp/advent-of-code/day1/input-part1")
(defn get-int-input [x]
  (->> (str/split-lines (slurp x))
       (map #(Integer/parseInt %))))

(defn part1 []
  (prn (count (->> (partition 2 1 (get-int-input part1-file))
                   (map #(if (< (first %) (second %)) "increased" "decreased"))
                   (filter #(= "increased" %))))))
