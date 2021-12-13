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

(def part2-data
  (->> (input-reader/split-line-input-at part2-file #",")
       (flatten)
       (map #(Long/parseLong %))))
(defn dist [x y]
 (Math/abs (- x y)))

(defn fuel
  ([[x y]]
   (dist x y))
  ([x y]
   (dist x y)))

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


; part2
(defn expensive-fuel
  ([[x y]]
   (expensive-fuel x y))
  ([x y]
   (let [a 0
         b (- (max x y) (min x y))]
     (-> (+ a b)
         (/ 2)
         (* (inc (dist a b)))))))

(defn total-expensive-fuel-at [x crabs]
  (apply +
         (->> crabs
              (map #(expensive-fuel x %)))))

(defn all-paths-expensive [crabs]
  (->> (range 0 (inc (apply max crabs)))
       (map #(total-expensive-fuel-at % crabs))))

(defn part2 []
  (apply min (all-paths-expensive part2-data)))

(comment
  (expensive-fuel 16 5)
  (time (int (part2))))


