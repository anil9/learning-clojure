(ns learning-clojure.codewars.adjacent-element-product-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.adjacent-element-product :refer [adjacent-element-product]]))

(deftest adjacent-element-product-test
  (is (= 6 (adjacent-element-product [1 2 3])))
  (is (= 50 (adjacent-element-product [9 5 10 2 24 -1 -48])))
  (is (= -14 (adjacent-element-product [-23 4 -5 99 -27 329 -2 7 -921])))
  (is (= 12 (adjacent-element-product (range 1 5))))
  (is (= 3721 (adjacent-element-product [-12, 58, -91, -31, 82, -26, -17, -87, 58, 23, 61, 61, 54])))

  )
