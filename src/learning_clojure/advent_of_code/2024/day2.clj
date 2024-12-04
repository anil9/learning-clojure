(ns learning-clojure.advent-of-code.2024.day2
  (:require
   [clojure.string :as str]
   [learning-clojure.advent-of-code.2024.input-reader :as input-reader]))

;(def input (input-reader/get-line-separated-input "day2.sample"))
(def input (input-reader/get-line-separated-input "day2.real"))

(defn- arr-str-to-int [arr]
  (map #(Integer/parseInt %) arr))
  

(defn report-of-integers [s]
  (let [str_numbers (->> s (map #(str/split %  #"\s+")))]
    (->> str_numbers
        (map #(arr-str-to-int %)))))
  
(comment
  (->> input (map #(str/split %  #"\s+")))
  (report-of-integers input)
  ,)

(defn is-safe? [report]
  (let [report-asc (sort report)
        report-desc (sort > report)
        distances (map #(abs (- (first %) (second %))) (partition 2 1 report))]
    (and (every? #(<= 1 % 3) distances)
         (or (= report report-asc) (= report report-desc)))))

(defn solution-part1 []
  (count (->> (report-of-integers input)
              (filter is-safe?))))

(defn remove-at [v idx]
  (vec (concat (subvec v 0 idx) (subvec v (inc idx)))))

(defn- safe-version [unsafe-report]
 (loop [i 0]
   (let [current-report (remove-at unsafe-report i)]
     (cond
       (is-safe? current-report) current-report
       (= (inc i) (count unsafe-report)) nil
       :else ( recur (inc i))))))
     
  
(comment
  (remove-at [1 5 2 3] 1)
  (is-safe? [5 2 3])
  (count [5 2 3])
  (safe-version [1 5 2 3])
  (safe-version [1 5 2])
  (safe-version [5 4 5 2])
  (is-safe? [8 4 2])
  ,)

(defn- make-them-safe [currently-unsafe]
  (count
    (->> currently-unsafe
      (map vec)
      (map safe-version)
      (filter #(not (nil? %))))))
  
(comment
  (make-them-safe '((1 5 2) (5 4 5 2)))
  ,)
  
       

(defn solution-part2 []
  (let [safe-part-1 (solution-part1)
        safe-part-2 (make-them-safe (filter #(not (is-safe? %)) (report-of-integers input)))]
    (+ safe-part-1 safe-part-2)))
(comment
  (is-safe? "3 2 1")
  (solution-part1)
  (->> input
              (map #(str/split % #"\s+"))
              (map first)
              (map #(Integer/parseInt %)))
  (solution-part1)
  (solution-part2)
  (filter #(not (is-safe? %)) input)
  (is-safe? (first input)))
  
  
