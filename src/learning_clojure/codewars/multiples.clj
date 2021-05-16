(ns learning-clojure.codewars.multiples)

(defn multiple-of [n m]
  (= 0 (mod n m)))

(defn solution [number]
  (->> (range number)
       (filter #(or (multiple-of % 3) (multiple-of % 5)))
       (reduce +)))
