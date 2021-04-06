(ns learning-clojure.count-the-digit)

(defn square [x]
  (* x x))

(defn count-occurrence [d coll]
  "Count occurrences of d in coll"
  (count (re-seq (re-pattern (str d)) coll)))

(defn nb-dig [n d]
  "Calculate number of digits d in sequence of squared n values from 0 to n (inclusive)"
  (->> (range (inc n))
       (map square)
       (map str)
       (map (partial count-occurrence d))
       (reduce +)))