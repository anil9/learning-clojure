(ns learning-clojure.codewars.counting-duplicates-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.counting-duplicates :refer [duplicate-count]]))

(deftest sample-tests
  (testing "blank"
    (is (= 0 (duplicate-count ""))))
  (testing "abcde"
    (is (= 0 (duplicate-count "abcde"))))
  (testing "aabbcde"
    (is (= 2 (duplicate-count "aabbcde"))))
  (testing "ignore-case"
    (is (= 2 (duplicate-count "aabBcde"))))
  (testing "Indivisibility"
    (is (= 1 (duplicate-count "Indivisibility"))))
  (testing "non-adjacent"
    (is (= 2 (duplicate-count "Indivisibilities")))))
