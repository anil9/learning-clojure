(ns learning-clojure.amicable-numbers)
(ns clojure.test.example  (:use clojure.test))

(defn proper-divisors [x]
  (filter #(= (mod x %) 0) (range 1 x)))

(deftest test-divisors
  (is (= '(1 2 4 71 142) (proper-divisors 284)))
  (is (= '(1 2 4 5 10 11 20 22 44 55 110) (proper-divisors 220))))

(defn potential-amicable [x]
  (reduce + (proper-divisors x)))

(defn sum-amicable-pair [x]
  (let [a1 (potential-amicable x) a2 (potential-amicable a1)]
    (if (= a2 x)
      (+ a1 a2)
      0)))

(deftest test-sum-amicable-pair
  (is (= 504 (sum-amicable-pair 220)))
  (is (= 504 (sum-amicable-pair 284))))

(defn amicable-pair? [x]
  (let [a1 (potential-amicable x) a2 (potential-amicable a1)]
    (if (= a2 x)
      a1)
    false))

(deftest test-amicable-pair
  (is (true? (amicable-pair? 220)))
  (is (true? (amicable-pair? 284)))
  (is (false? (amicable-pair? 2))))


;(println (reduce + (map sum-amicable-pair (range 1 10000))) 2)
(defn amicable-numbers-sum-in-range [range])


(run-all-tests)