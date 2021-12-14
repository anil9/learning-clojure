(ns learning-clojure.advent-of-code.2021.day8
  (:require
    [learning-clojure.advent-of-code.2021.input-reader :as input-reader]
    [clojure.string :as str]
    [clojure.set :as sets]))
(def day "day8")
(def part1-example-file (str day "/example-input-part1"))
(def part1-file (str day "/input-part1"))
(def part2-example-file (str day "/example-input-part2"))
(def part2-file (str day "/input-part2"))

(defn input-at-line [line]
  (let [line-input (->> (str/split line #"\|")
                        (map #(str/trim %)))]
    {:signal-patterns (str/split (first line-input) #"\s+")
     :output-value    (str/split (second line-input) #"\s+")}))
(def part1-data
  (->> (input-reader/get-line-separated-input part1-file)
       (map #(input-at-line %))))

(def easy-numbers {:1 2
                   :4 4
                   :7 3
                   :8 7})

(defn count-easy-numbers [m]
  (let [count-easy-numbers (set (vals easy-numbers))
        output-value (:output-value m)]
    (count
      (->> output-value
           (filter #(contains? count-easy-numbers (count %)))))))

(defn part1 []
  (apply + (->> part1-data
                (map #(count-easy-numbers %)))))

(comment
  (part1))