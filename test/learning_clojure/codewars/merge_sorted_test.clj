(ns learning-clojure.codewars.merge-sorted-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.merge-sorted :refer [mergesorted]]))



(deftest mergesorted-test
  (is (= (mergesorted [1 2] [3]) (range 1 4)))
  (is (= (mergesorted [1 2] [3 4]) (range 1 5)))
  (is (= (mergesorted [1] [2 3 4]) (range 1 5)))
  (is (= (mergesorted [] [1 2 3 4]) (range 1 5)))
  (is (= (mergesorted [1 2 3 4] []) (range 1 5))))
