(ns learning-clojure.codewars.range-parser-test
  (:require [clojure.test :refer :all]
            [learning-clojure.codewars.range-parser :refer [range-parser]]))

(deftest range-parser-test
  (is (= (vec (range 11)) (range-parser "0-10")))
  (is (= [0 2 4] (range-parser "0-4:2")))
  (is (= [0 2 4 5] (range-parser "0-4:2,5")))
  (is (= [0 2 4 5] (range-parser "0-4:2, 5")))
  (is (= [1 6] (range-parser "1-10:5"))))

