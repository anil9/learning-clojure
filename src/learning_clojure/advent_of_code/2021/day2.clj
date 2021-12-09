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

(def vertical-command #{"up" "down"})

(defn vertical-position [coll]
  (apply + (->> coll
                (filter #(contains? vertical-command (first %)))
                (map get-vertical-value))))

(def part1-data
  (input-reader/split-line-input-at part1-file " "))

(defn part1 []
  (let [vertical (vertical-position part1-data)
        horizontal (horizontal-position part1-data)]
    (* vertical horizontal)))
;; part 2

(def part2-data
  (input-reader/split-line-input-at part2-file " "))

(def aim-command #{"up" "down"})

(defn get-aim [coll]
  (apply + (->> coll
                (filter #(contains? aim-command (first %)))
                (map get-vertical-value))))

(defn calculate-depth [coll idx itm]
    (if (contains? aim-command (first itm))
      0
      (* (get-aim (take idx coll))
         (Integer/parseInt (second itm)))))

(defn get-total-depth [coll]
  (apply + (map-indexed #(calculate-depth coll %1 %2) coll)))


(defn part2 []
  (* (horizontal-position part2-data)
    (get-total-depth part2-data)))

(prn
  (part2))