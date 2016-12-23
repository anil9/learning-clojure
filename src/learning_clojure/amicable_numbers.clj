(ns learning-clojure.amicable-numbers)
(ns clojure.test.example  (:use clojure.test))

(defn proper-divisors [x]
  (filter #(= (mod x %) 0) (range 1 x)))

(deftest test-divisors
  (is (= '(1 2 4 71 142) (proper-divisors 284)))
  (is (= '(1 2 4 5 10 11 20 22 44 55 110) (proper-divisors 220)))
  (is (= '(1) (proper-divisors 7)))
  (is (= '(1) (proper-divisors 13)))
  (is (= '(1) (proper-divisors 31))))


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
    (if (and (not (= x a1))(= a2 x))
      a1
      false)))

(deftest test-amicable-pair
  (is (= 284 (amicable-pair? 220)))
  (is (= 220 (amicable-pair? 284)))
  (is (false? (amicable-pair? 2))))


;(println (reduce + (map sum-amicable-pair (range 1 10000))) 2)
(defn build-amicable-numbers-map [x value-sum]
      (if-not (contains? value-sum x)
        (let [amicable (amicable-pair? x)]
          (if-not (false? amicable)
            (conj value-sum {amicable (+ x amicable)})
            value-sum))
        value-sum))

(deftest test-build-map
  (is (= {284 504} (build-amicable-numbers-map 220 {})))
  (is (= {220 504} (build-amicable-numbers-map 284 {})))
  (is (= {284 504} (build-amicable-numbers-map 284 {284 504}))))
;(reduce + (vals value-sum))

;(println (amicable-numbers-sum-in-range 100))
(defn rec-build-map [x value-map stop]
  (if (== x stop) value-map
                  (recur
                            (+ x 1)(build-amicable-numbers-map x value-map)stop)))
(defn filter-high-amicable [[k v]]
  (if (< 10000 k)
    [k (- v k)]
    [k v]))

(println (reduce + (map last
                     (map filter-high-amicable
                          (rec-build-map 1 {} (- 10000 1))))))
(run-all-tests)