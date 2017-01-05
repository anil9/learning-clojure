(ns learning-clojure.abundant-numbers)
(require '[clojure.test :as test :refer :all]
         '[learning-clojure.core :refer [proper-divisors]])

;; checks if the number is an abundant number
(defn abundant-number? [x]
  (< x (reduce + (proper-divisors x))))

(deftest test-abundant
  (is true? (abundant-number? 12))
  (is true? (abundant-number? 24))
  (is false? (abundant-number? 3))
  (is true? (abundant-number? 28123)))


;; extracts all abundant numbers between min and max (inclusive)
(defn extract-abundant-numbers [min max]
  (loop [counter min
         stop max
         abundant-numbers []]
    (if (> counter stop)
      abundant-numbers
      (if (abundant-number? counter)
        (recur (inc counter) stop (conj abundant-numbers counter))
        (recur (inc counter) stop abundant-numbers)))))

(deftest test-extract-abundant-numbers
  (is (= '(12 18 20 24 30) (extract-abundant-numbers 12 30)))
  (is (= '(40 42 48 54 56 60) (extract-abundant-numbers 37 65))))


;(defn all-possible-sums [elements]
;  (set (flatten (map-indexed (fn [idx val] (map (fn [x] (+ val x)) (drop (inc idx) elements))) elements))))
(defn all-possible-sums [elements]
  (set (flatten (map-indexed (fn [idx val] (map (fn [x] (+ val x)) (drop idx elements))) elements))))

(deftest test-possible-sums
  (is (= '(2 3 4 5 6) (sort (all-possible-sums '(1 2 3)))))
  (is (= '(2 3 4 5 6) (sort (all-possible-sums '(1 1 2 3)))))
  (is (= '(6 8 10) (sort (all-possible-sums '(3 5))))))


(def lower-bound 12)
(def upper-bound 28123)

;; extracts all elements from new-list that wasn't in old-list
(defn extract-new-elements [old-list new-list]
  (remove #(contains? (set old-list) %) (set new-list)))

(deftest test-extract-new-elements
  (is (= (range 5) (sort (extract-new-elements (range 5 10) (range 10)))))
  (is (= '(11 32 77 99 108) (sort (extract-new-elements '(70 71 72) '(11 32 70 77 72 108 99))))))

;; Gives result for euler #23
(def sums (all-possible-sums (extract-abundant-numbers lower-bound upper-bound)))
(println (reduce +' (extract-new-elements sums (range 1 (inc upper-bound)))))

(run-tests)