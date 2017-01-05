(ns learning-clojure.gcd)
(require '[clojure.test :as test :refer :all])

;; calculates GCD of two integers
(defn gcd [x y]
  (cond
    (or (= 0 x) (= 0 y)) (max x y)
    (> x y) (recur (mod x y) y)
    (< x y) (recur x (mod y x))
    :else x))

(deftest test-gcd
         (is (= 12 (gcd 12 12)))
         (is (= 5 (gcd 15 20)))
         (is (= 1 (gcd 1 5)))
         (is (= 10 (gcd 10 100)))
         (is (= 1 (gcd 22 131))))