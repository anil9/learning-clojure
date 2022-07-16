(ns learning-clojure.codewars.unique-in-order)

(defn append-unique-neighbour [res neighbour]
  (if (not= (last res) neighbour)
    (conj res neighbour)
    res))


(defn unique-in-order [input]
  (loop [res []
         current input]
    (if (empty? current)
      res
      (recur (append-unique-neighbour res (first current)) (drop 1 current)))))
