(ns learning-clojure.codewars.range-parser
  (:require [clojure.string :as string]))
;https://www.codewars.com/kata/57d307fb9d84633c5100007a/train/clojure

(defn filter-indexed [f coll]
  (map #(nth coll %) 
       (->> (range (count coll))
            (filter #(f % (nth coll %))))))

(defn create-range [s]
  (let [r (string/split s #"-")
        filter-group (string/split (second r) #":")
        from (Integer/parseInt (first r))
        to (inc (Integer/parseInt (first filter-group)))
        divider (when (second filter-group)
                  (Integer/parseInt (second filter-group)))]
    (if divider
      (filter-indexed (fn [x _](= 0 (mod x divider))) (range from to))
      (vec (range from to)))))

(defn range-parser [s]
  (->> (string/split s #",\s?")
       (map #(if (re-matches #"\d+" %)
               (Integer/parseInt %)
               (create-range %)))
       (flatten)))

(comment
  (string/split "4:2" #":")
  (second (string/split "4" #":"))
  (string/split "1,2" #",\s?")
  (string/split "1, 2" #",\s?")
  (re-matches #"\d+" "1,2")
  (map-indexed))
