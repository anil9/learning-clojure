(ns learning-clojure.codewars.prodseq-test
  (:require [clojure.test :refer :all]
            [learning-clojure.codewars.prodseq :refer [solve calc-prod]]))

(defn eq [expected actual]
  (let [p (calc-prod expected)
        q (calc-prod actual)]
    (= p q)))

(deftest solve-test
  (is (eq [2N 11N] (solve [2 1 3 4])))
  (is (eq [250N 210N] (solve [1 3 1 2 1 5 1 9])))
  (is (eq [13243200N 25905600N] (solve [3 9 8 4 6 8 7 8 4 8 5 6 6 4 4 5])))
  (is (eq [639, 1788] (solve [21, 24, 15, 22, 1, 2])))
  (is (eq [2344, 2892] (solve [2, 1, 3, 4, 2, 2, 1, 5, 2, 3, 4, 5]))))
