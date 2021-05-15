(ns learning-clojure.codewars.sort-the-odd-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.sort-the-odd :refer [sort-array]]))

(deftest sort-array-test
  (is (= [] (sort-array [])))
  (is (= [1 3 2 8 5 4] (sort-array [5 3 2 8 1 4])))
  (is (= [1 3 5 8 0] (sort-array [5 3 1 8 0]))))
