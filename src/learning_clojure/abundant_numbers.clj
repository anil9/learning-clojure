(ns learning-clojure.abundant-numbers
  (:use clojure.test)
  (:use learning-clojure.amicable-numbers))

;; checks if the number is an abundant number
(defn abundant-number? [x]
  (< x (reduce + (proper-divisors x))))

(deftest test-abundant
  (is true? (abundant-number? 12))
  (is true? (abundant-number? 24))
  (is false? (abundant-number? 3))
  (is true? (abundant-number? 28123)))


;; extracts all abundant numbers (<= 12,  >= 28123)
(defn extract-abundant-numbers []
  (loop [counter 12 stop 28123 abundant-numbers []]
    (if (> counter stop)
      abundant-numbers
      (if (abundant-number? counter)
        (recur (inc counter) stop (conj abundant-numbers counter))
        (recur (inc counter) stop abundant-numbers)))))



(defn mul-over-list [abundant-numbers x]
  (map #(* x %) abundant-numbers))

(deftest test-mul-list
  (is (= '(2 4 6) (mul-over-list '(1 2 3) 2))))

(defn all-possible-sums [elements]
    ;(println (map (fn [x] (* testvar x)) abundant-numbers))))
    (map-indexed (fn [idx val] (map (fn [x] (* val )) (drop (inc idx) elements))) elements))

;(println (map ((fn [x y] (* y x)) testvar) extract-abundant-numbers))


;felet ligger i extract-abundant-numbers. Kan ej skapa en lista av tal av den

(println (count (distinct(flatten(all-possible-sums (extract-abundant-numbers))))))

(println "test")