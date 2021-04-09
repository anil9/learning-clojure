(ns learning-clojure.codewars.odd-or-even-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.odd-or-even :refer [odd-or-even]]))

(deftest odd-or-even-test
  (is (= "even" (odd-or-even [0])))
  (is (= "even" (odd-or-even [2])))
  (is (= "odd" (odd-or-even [1])))
  (is (= "even" (odd-or-even [17 17])))
  (is (= "odd" (odd-or-even [17 18])))
  )
