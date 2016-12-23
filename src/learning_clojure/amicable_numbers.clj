(ns learning-clojure.amicable-numbers (:use clojure.test))


;; finds all divisors of x
(defn proper-divisors [x]
  (filter #(= (mod x %) 0) (range 1 x)))

(deftest test-divisors
  (is (= '(1 2 4 71 142) (proper-divisors 284)))
  (is (= '(1 2 4 5 10 11 20 22 44 55 110) (proper-divisors 220)))
  (is (= '(1) (proper-divisors 7)))
  (is (= '(1) (proper-divisors 13)))
  (is (= '(1) (proper-divisors 31))))

;; sums all divisors
(defn sum-divisors [x]
  (reduce + (proper-divisors x)))

;; If x is an amicable pair (x, p2) return p2, else return false
(defn amicable-pair? [x]
  (let [a1 (sum-divisors x) a2 (sum-divisors a1)]
    (if (and (not (= x a1))(= a2 x))
      a1
      false)))

(deftest test-amicable-pair
  (is (= 284 (amicable-pair? 220)))
  (is (= 220 (amicable-pair? 284)))
  (is (false? (amicable-pair? 2))))

;; takes a number x. If its an amicable pair (x, p2) conj value-sum with {p2 x+p2}
(defn conj-amicable-numbers-map [x value-sum]
      (if-not (contains? value-sum x)
        (let [amicable (amicable-pair? x)]
          (if-not (false? amicable)
            (conj value-sum {amicable (+ x amicable)})
            value-sum))
        value-sum))

(deftest test-build-map
  (is (= {284 504} (conj-amicable-numbers-map 220 {})))
  (is (= {220 504} (conj-amicable-numbers-map 284 {})))
  (is (= {284 504} (conj-amicable-numbers-map 284 {284 504}))))


;;recursively builds a map of {amicable1 (+amicable1 amicable2} from x to range
(defn rec-build-map [x value-map stop]
  (if (== x stop) value-map
                  (recur
                    (+ x 1) (conj-amicable-numbers-map x value-map) stop)))

;; no amicable number > 10000 allowed
(defn filter-high-amicable [[k v]]
  (if (< 10000 k)
    [k (- v k)]
    [k v]))

;; prints the result
(println (reduce + (map last
                     (map filter-high-amicable
                          (rec-build-map 1 {} (- 10000 1))))))
(run-tests)