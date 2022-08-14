(ns learning-clojure.codewars.dblinear-test
  (:require [clojure.test :refer :all]
            [learning-clojure.codewars.dblinear :refer [dblinear]]))

(deftest dblinear-test
  (is (= (dblinear 0) 1))
  (is (= (dblinear 10) 22))
  (is (= (dblinear 20) 57))
  (is (= (dblinear 30) 91))
  (is (= (dblinear 50) 175))
  (is (= (dblinear 4203) 52651)))
