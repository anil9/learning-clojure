(ns learning-clojure.codewars.phone-test
  (:require [clojure.test :refer :all]
            [learning-clojure.codewars.phone :refer [create-phone-number]]))

(deftest create-phone-number-test
  (is (= (create-phone-number (vec (concat (range 1 10) [0]))) "(123) 456-7890")))
