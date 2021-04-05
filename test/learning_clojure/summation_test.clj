(ns learning-clojure.summation-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.summation :refer [summation]]))

(deftest summation-test
  (is (= 3 (summation 2)))
  (is (= 36 (summation 8)))
  (is (= 1 (summation 1)))
  )
