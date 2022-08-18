(ns learning-clojure.codewars.prodseq)
;https://www.codewars.com/kata/5e4bb05b698ef0001e3344bc/clojure
; solution is too slow
(defn square [x]
  (* x x))

(defn calc-prod [arr]
  (apply * (->> (partition 2 arr) 
                (map #(map square %))
                (map #(apply + %)))))
      
(defn find-building-numbers [target]
  (loop [a 0N
         b (bigint (Math/sqrt target))]
    (let [current-prod (calc-prod [a b])]
      (if (= current-prod target)
        [a b]
        (if (= a b)
          [0 0]
          (if (< current-prod target)
            (recur (inc a) b)
            (recur a (dec b))))))))

(defn solve [arr]
  (let [big-arr (map bigint arr)
        prod (calc-prod big-arr)]
      (find-building-numbers prod)))
