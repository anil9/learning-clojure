(ns learning-clojure.codewars.unique-in-order-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.unique-in-order :refer [unique-in-order]]))

(deftest unique-in-order-test
  (testing "do-nothing-vector"
    (is (= (unique-in-order [1 2 3]), [1 2 3])))

  (testing "do-nothing-string"
    (is (= (unique-in-order "ABC"), [\A \B \C])))

  (testing "do-nothing-list"
    (is (= (unique-in-order '(1 2 3)), [1 2 3])))

  (is (= (unique-in-order [0 0 0 0 1 1 1 2 2 0 0 1 1 1]) [0 1 2 0 1]))
  (is (= (unique-in-order "AAAABBBCCDAABBB") [\A \B \C \D \A \B]))
  (is (= (unique-in-order "AAAABBB") [\A \B])))

