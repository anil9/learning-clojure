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

(deftest bingo-board?-test
  (is (not (true? (bingo-board? board #{22 13 17 11 818}))))
  (is (true? (bingo-board? board #{22 13 17 11 0})))
  (is (true? (bingo-board? board #{22 8 21 6 1})))
  (is (true? (bingo-board? board #{0 24 7 5 19}))))
