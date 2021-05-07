(ns learning-clojure.codewars.merge-sorted)


(defn mergesorted
  [[a_x & a_xs :as a] [b_x & b_xs :as b]]
  (cond
    (nil? a_x) b
    (nil? b_x) a
    :else (concat a b)))
