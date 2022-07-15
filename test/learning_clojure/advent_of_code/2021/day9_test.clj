(ns learning-clojure.advent-of-code.2021.day9-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.advent-of-code.2021.day9 :refer [generate-possible-points]]))

(def matrix
  [[2 1 9 9 9 4 3 2 1 0]
   [3 9 8 7 8 9 4 9 2 1]
   [9 8 5 6 7 8 9 8 9 2]
   [8 7 6 7 8 9 6 7 8 9]
   [9 8 9 9 9 6 5 6 7 8]])

(deftest generate-possible-points-test
  (testing "Corners have two possible points"
    (is (= #{'(0 1) '(1 0)} (set (generate-possible-points [0 0] matrix))))
    (is (= #{'(9 1) '(8 0)} (set (generate-possible-points [9 0] matrix))))
    (is (= #{'(0 8) '(1 9)} (set (generate-possible-points [0 9] matrix))))
    (is (= #{'(9 8) '(8 9)} (set (generate-possible-points [9 9] matrix)))))
  (testing "edges have three possible points"
    (is (= #{'(0 0) '(1 1) '(0 2)} (set (generate-possible-points [0 1] matrix))))
    (is (= #{'(1 0) '(2 1) '(3 0)} (set (generate-possible-points [2 0] matrix))))
    (is (= #{'(9 1) '(8 2) '(9 3)} (set (generate-possible-points [9 2] matrix))))
    (is (= #{'(1 9) '(2 8) '(3 9)} (set (generate-possible-points [2 9] matrix)))))
  (testing "other points have four possible points"
    (is (= #{'(1 2) '(3 2) '(2 1) '(2 3)} (set (generate-possible-points [2 2] matrix))))))
