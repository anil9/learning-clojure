(ns learning-clojure.codewars.summation)
(defn summation [n]
  (reduce + (range (+ n 1))))