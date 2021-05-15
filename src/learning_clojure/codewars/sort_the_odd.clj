(ns learning-clojure.codewars.sort-the-odd)
;https://www.codewars.com/kata/578aa45ee9fd15ff4600090d/train/clojure

(defn sorted-odd [coll]
  (sort (->> coll
             (filter odd?))))

(defn odd-idx [coll]
  (->> coll
       (map-indexed #(if (odd? %2) %1))
       (filter some?)))

(defn sort-array [xs]
  (prn xs)
  (let [arr (into [] xs)
        replacement-map (into {} (map vector (odd-idx xs) (sorted-odd xs)))]
    (reduce-kv (fn [r k v] (assoc r k v)) arr replacement-map)))
