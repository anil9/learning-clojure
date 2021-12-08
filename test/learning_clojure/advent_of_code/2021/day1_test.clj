(ns learning-clojure.advent-of-code.2021.day1-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.advent-of-code.2021.day1 :refer :all]))

(deftest count-increases-between-elements-test
  (is (= 2 (count-increases-between-elements (range 3))))
  (is (= 0 (count-increases-between-elements (reverse (range 3)))))
  (is (= 3 (count-increases-between-elements '(8 5 200 1 0 0 1 2)))))


(deftest test-answers-day-1
  (is (= 1553 (part1)))
  (is (= 1597 (part2))))