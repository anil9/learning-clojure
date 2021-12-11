(ns learning-clojure.advent-of-code.2021.day5-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.advent-of-code.2021.day5 :refer :all]))

(deftest horizontal-or-vertical-line-at-point?-test
  (is (false? (horizontal-or-vertical-line-at-point? [0 0] [[0 0] [1 1]])))
  (is (false? (horizontal-or-vertical-line-at-point? [0 0] [[1 1] [0 0]])))
  (is (true? (horizontal-or-vertical-line-at-point? [1 2] [[1 1] [1 3]])))
  (is (false? (horizontal-or-vertical-line-at-point? [2 0] [[1 1] [0 0]])))
  (is (false? (horizontal-or-vertical-line-at-point? [6 4] [[6 4] [2 0]]))))

(def lines '(((0 9) (5 9))
             ((8 0) (0 8))
             ((9 4) (3 4))
             ((2 2) (2 1))
             ((7 0) (7 4))
             ((6 4) (2 0))
             ((0 9) (2 9))
             ((3 4) (1 4))
             ((0 0) (8 8))
             ((5 5) (8 2))))

(deftest overlapping-lines-at-test
  (is (= (overlapping-lines-at [0 0] '([[0 0] [1 1]])) 0))
  (is (= (overlapping-lines-at [7 0] lines) 1))
  (is (= (overlapping-lines-at [2 1] lines) 1))
  (is (= (overlapping-lines-at [2 4] lines) 1))
  (is (= (overlapping-lines-at [3 4] lines) 2))
  (is (= (overlapping-lines-at [7 4] lines) 2))
  (is (= (overlapping-lines-at [8 4] lines) 1))
  (is (= (overlapping-lines-at [9 4] lines) 1))
  (is (= (overlapping-lines-at [0 9] lines) 2))
  (is (= (overlapping-lines-at [1 9] lines) 2))
  (is (= (overlapping-lines-at [2 9] lines) 2))
  (is (= (overlapping-lines-at [3 9] lines) 1))
  (is (= (overlapping-lines-at [9 9] lines) 0))
  (is (= (overlapping-lines-at [6 4] lines) 1)))


(deftest lines-intersect?-test
  (is (= (lines-intersect '([3 4] [1 4]) '([9 4] [3 4])) #{[3 4]}))
  (is (= (lines-intersect '([7 0] [7 4]) '([9 4] [3 4])) #{[7 4]}))
  (is (= (lines-intersect '([0 9] [2 9]) '([0 9] [5 9])) #{[0 9] [1 9] [2 9]}))
  (is (empty? (lines-intersect '([8 0] [0 8]) '([0 9] [5 9]))))
  (is (empty? (lines-intersect '([0 9] [2 9]) '([2 2] [2 1])))))
