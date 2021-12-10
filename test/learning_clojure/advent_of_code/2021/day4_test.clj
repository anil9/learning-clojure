(ns learning-clojure.advent-of-code.2021.day4-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.advent-of-code.2021.day4 :refer :all]))

(deftest bingo-row?-test
  (is (true? (bingo-row? [1 2] #{3 1 2})))
  (is (true? (bingo-row? [0 2] #{0 2 1})))
  (is (true? (bingo-row? [-1 2] #{0 2 1 -1})))
  (is (false? (bingo-row? [1 2] #{3 4 2})))
  (is (false? (bingo-row? [1 2 3 4 5] #{3 4 2}))))

(def board [[22 13 17 11 0]
            [8 2 23 4 24]
            [21 9 14 16 7]
            [6 10 3 18 5]
            [1 12 20 15 19]])
(def second-board
  [[3 15 0 2 22]
   [9 18 13 17 5]
   [19 8 7 25 23]
   [20 11 10 24 4]
   [14 21 16 12 6]])



(deftest bingo-board?-test
  (is (not (true? (bingo-board? board #{22 13 17 11 818}))))
  (is (true? (bingo-board? board #{22 13 17 11 0})))
  (is (true? (bingo-board? board #{22 8 21 6 1})))
  (is (true? (bingo-board? board #{0 24 7 5 19})))
  (is (true? (bingo-board? second-board #{7,4,9,5,11,17,23,2,0,14,21,24,10,16,13}))))
