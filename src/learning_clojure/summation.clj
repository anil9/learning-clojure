(ns learning-clojure.summation)
(defn summation [n]
  (reduce + (range (+ n 1))))