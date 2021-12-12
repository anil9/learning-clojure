(ns learning-clojure.advent-of-code.2021.day5-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.advent-of-code.2021.day5 :refer :all]))

(deftest lines-intersect?-test
  (is (= (lines-intersect '([3 4] [1 4]) '([9 4] [3 4])) #{[3 4]}))
  (is (= (lines-intersect '([7 0] [7 4]) '([9 4] [3 4])) #{[7 4]}))
  (is (= (lines-intersect '([0 9] [2 9]) '([0 9] [5 9])) #{[0 9] [1 9] [2 9]}))
  (is (empty? (lines-intersect '([8 0] [0 8]) '([0 9] [5 9]))))
  (is (empty? (lines-intersect '([0 9] [2 9]) '([2 2] [2 1])))))

(deftest lines-intersect-with-diag-test)


(deftest determine-k-m-test
  (is (= (determine-k-m [[0 0] [3 3]]) {:k 1 :m 0}))
  (is (= (determine-k-m [[9 7] [7 9]]) {:k -1 :m 16})))


(deftest gen-points-for-line-test
  (is (= (set (gen-points-for-line [[1 1] [3 3]])) (set [[1 1] [2 2] [3 3]])))
  (is (= (set (gen-points-for-line [[9 7] [7 9]])) (set [[9 7] [8 8] [7 9]]))))

