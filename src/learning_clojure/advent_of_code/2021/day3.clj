(ns learning-clojure.advent-of-code.2021.day3
  (:require [learning-clojure.advent-of-code.2021.input-reader :as input-reader]))

(def day "day3")
(def part1-example-file (str day "/example-input-part1"))
(def part1-file (str day "/input-part1"))
(def part2-example-file (str day "/example-input-part2"))
(def part2-file (str day "/input-part2"))

;; gamma rate is the most frequent char comparing all lists for each corresponding index (left-to-right)
;; result is binary.
;; To calculate epsilon simply negate each bit in gamma
(def part1-data
  (input-reader/get-line-separated-input part1-file))

;; enklare om varje sträng innehöll talet som ska undersökas
;; '("AAA" "BBB" "CCC" "DDD") -> '("ABCD" "ABCD" "ABCD")
;; summan av det talet > (/ (count talet) 2)

(def number-base 10)
(defn half [n]
  (/ n 2))

(defn sum [coll]
  (apply + coll))

(defn most-frequent-nth-bit [coll idx]
  (let [numbers-to-compare (->> coll
                                (map #(nth % idx))
                                (map #(Character/digit % number-base)))]
      (if (> (sum numbers-to-compare) (half (count numbers-to-compare)))
        "1"
        "0")))
(defn gamma-str [coll]
  (apply str
         (->> (range (count (first coll)))
              (map #(most-frequent-nth-bit coll %)))))

(defn bin-str-to-dec [bin]
  (Integer/parseInt bin 2))

(defn invert-bin [bin]
  (apply str
         (->> bin
              (map #(if (= \0 %) "1" "0")))))

(defn part1 []
  (let [gamma (gamma-str part1-data)
        gamma-dec (bin-str-to-dec gamma)
        epsilon-dec (bin-str-to-dec (invert-bin gamma))]
    (* gamma-dec epsilon-dec)))

;(prn (part1))
; Part 2
(def part2-data
  (input-reader/get-line-separated-input part2-file))

(defn most-frequent-nth-bit-with-default [coll idx default]
  (let [numbers-to-compare (->> coll
                                (map #(nth % idx))
                                (map #(Character/digit % number-base)))]
    (cond
      (> (sum numbers-to-compare) (half (count numbers-to-compare))) "1"
      (< (sum numbers-to-compare) (half (count numbers-to-compare))) "0"
      :else default)))

(defn oxygen-rating [input]
  (bin-str-to-dec
    (loop [coll input
           idx 0]
      (if (= 1 (count coll))
        (first coll)
        (let [wanted (most-frequent-nth-bit-with-default coll idx "1")]
          (recur (->> coll
                      (filter #(= wanted (str (nth % idx)))))
                 (inc idx)))))))

(defn co2-rating [input]
  (bin-str-to-dec
    (loop [coll input
           idx 0]
      (if (= 1 (count coll))
        (first coll)
        (let [wanted (invert-bin (most-frequent-nth-bit-with-default coll idx "1"))]
          (recur (->> coll
                      (filter #(= wanted (str (nth % idx)))))
                 (inc idx)))))))

(defn part2 []
  (* (oxygen-rating part2-data)
     (co2-rating part2-data)))

;(prn (part2))