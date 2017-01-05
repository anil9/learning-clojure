(ns learning-clojure.fibonacci)
(require '[clojure.test :as test :refer :all])

;; fibonacci
(defn fib [x]
  (map first (take x (iterate (fn [[a b]] [b (+' a b)]) [1 1]))))

(deftest fibtest
  (is (= [1 1] (fib 2)))
  (is (= [1 1 2 3 5] (fib 5))))

(run-tests)