(ns learning-clojure.pascals-triangle)
(require '[clojure.test :as test :refer :all]
         '[learning-clojure.core :refer [fact]])

;;calculation for pascals triangle given the row (n) and the column (r)
(defn pascal-calc [n r]
  (quot (fact n) (* (fact r) (fact (- n r)))))

;; returns the row n according to pascal's triangle
(defn pascal [n]
  (map-indexed (fn [idx elem] (pascal-calc n idx)) (range (inc n))))

(deftest test-pascal
  (is (= '(1) (pascal 0)))
  (is (= '(1 1) (pascal 1)))
  (is (= '(1 3 3 1) (pascal 3))))

;; printing pascals triangle to STDOUT
(defn pascal-triangle [n]
  (dorun (map #(apply println (pascal %)) (range n))))

;; prints pascal's triangle of depth 10
(pascal-triangle 10)
(run-tests)
