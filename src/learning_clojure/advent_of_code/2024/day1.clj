(ns learning-clojure.advent-of-code.2024.day1 
  (:require
   [clojure.string :as str]
   [learning-clojure.advent-of-code.2024.input-reader :as input-reader]))

;(def input (input-reader/get-line-separated-input "day1.sample"))
(def input (input-reader/get-line-separated-input "day1.real"))

(defn- distance-sum [l1 l2] 
  (loop [l1 l1
         l2 l2
         sum 0]
    (if (or (empty? l1) (empty? l2)) sum 
      ;(do (prn (first l1) " - " (first l2) " = " (abs (- (first l1) (first l2))))
      (recur (drop 1 l1) (drop 1 l2) (+ sum (abs (- (first l1) (first l2))))))))
   
  
  

(defn solution-part1 []
  (let [l1 (sort  (->> input
                      (map #(str/split % #"\s+"))
                      (map first)
                      (map #(Integer/parseInt %))))
        l2 (sort(->> input
                    (map #(str/split % #"\s+"))
                    (map second)
                    (map #(Integer/parseInt %))))]
    (distance-sum l1 l2)))


(comment
  (->> input
              (map #(str/split % #"\s+"))
              (map first)
              (map #(Integer/parseInt %)))
  (solution-part1))
  
