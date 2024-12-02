(ns learning-clojure.advent-of-code.2024.day2
  (:require
   [clojure.string :as str]
   [learning-clojure.advent-of-code.2024.input-reader :as input-reader]))

(def input (input-reader/get-line-separated-input "day2.sample"))
;(def input (input-reader/get-line-separated-input "day1.real"))

(defn is-safe? [report]
  (let [ report-int (->> (str/split report #"\s+")
                         (map #(Integer/parseInt %)))
        report-asc (sort report-int)
        report-desc (sort > report-int)
        distances (map #(abs (- (first %) (second %))) (partition 2 1 report-int))]
    (and (every? #(<= 1 % 3) distances)
         (or (= report-int report-asc) (= report-int report-desc)))))

(defn solution-part1 []
  (count (->> input
            (filter is-safe?))))
       

(comment
  (is-safe? "3 2 1")
  (solution-part1)
  (->> input
              (map #(str/split % #"\s+"))
              (map first)
              (map #(Integer/parseInt %)))
  (solution-part1)
  (solution-part2))
  
  
