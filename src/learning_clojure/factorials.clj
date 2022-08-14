(ns learning-clojure.factorials)
(defn long-factorial [n]
   (str (->> (range 1N (inc n))
             (reduce *))))

(comment
  (long-factorial 40))


