(ns learning-clojure.codewars.merged-string-checker)

;(defn is-merge
;  "Returns whether a string is a merge of two other strings"
;  [s p1 p2]
;  (if (not= (count s) (+ (count p1) (count p2)))
;    false
;    (loop [s_idx 0
;           p1_idx 0
;           p2_idx 0]
;      (cond
;        (= s_idx (count s)) true
;        (and (= (count p1) p1_idx) (= (count p2) p2_idx)) false
;        :else (let [s_letter (nth s s_idx)
;                    p1_letter (when (< p1_idx (count p1)) (nth p1 p1_idx))
;                    p2_letter (when (< p2_idx (count p2)) (nth p2 p2_idx))]
;                (if (and (not= s_letter p1_letter) (not= s_letter p2_letter))
;                  false
;                  (let [next_p1_idx (if (= p1_letter s_letter) (inc p1_idx) p1_idx)
;                        next_p2_idx (if (and (= p2_letter s_letter) (= next_p1_idx p1_idx)) (inc p2_idx) p2_idx)]
;                    (recur (inc s_idx) next_p1_idx next_p2_idx))))))))

(defn is-merge
  [s p1 p2]
  (if (empty? s)
    (and (empty? p1) (empty? p2))
    (or
      (and (= (first s) (first p1)) (is-merge (rest s) (rest p1) p2))
      (and (= (first s) (first p2)) (is-merge (rest s) p1 (rest p2))))))