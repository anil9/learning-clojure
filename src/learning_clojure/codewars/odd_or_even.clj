(ns learning-clojure.codewars.odd-or-even)

(defn odd-or-even [xs]
  (let [sum (reduce + xs)]
    (cond
      (empty? xs) "even"
      (even? sum) "even"
      :else "odd")))