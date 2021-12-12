(ns learning-clojure.advent-of-code.2021.day6-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.advent-of-code.2021.day6 :refer :all]))

(deftest process-day-test
  (is (= (process-day [3 4 3 1 2]) [2 3 2 0 1]))
  (is (= (count (process-day (process-day [3 4 3 1 2]))) 6)))

