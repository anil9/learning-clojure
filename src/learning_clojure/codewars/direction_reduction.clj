(ns learning-clojure.codewars.direction-reduction)

(def opposite-ns #{"NORTH" "SOUTH"})
(def opposite-ew #{"EAST" "WEST"})

(defn redundant-directions? [x]
  (or (= opposite-ns (set x))
      (= opposite-ew (set x))))

(defn adj-redundant? [arr idx val]
  (or (redundant-directions? [val (get arr (dec idx))])
      (redundant-directions? [val (get arr (inc idx))])))

(defn reduc [arr]
  (->> arr
       (keep-indexed (fn [idx val]
                       (if (adj-redundant? (vec arr) idx val)
                         nil
                         val)))))

(defn dirReduc [arr]
  (let [less-redundant (reduc arr)]
    (if (= less-redundant (reduc less-redundant))
      (if (empty? less-redundant)
        nil
        less-redundant)
      (dirReduc less-redundant))))








