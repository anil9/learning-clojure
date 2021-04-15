(ns learning-clojure.codewars.buddy-pairs-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.buddy-pairs :refer [buddy
                                                           get-buddy
                                                           proper-divisors]]))
(deftest buddy-test
  (is (= "(1081184 1331967)" (buddy 1071625 1103735)))
  (is (= "Nothing" (buddy 200 1000)))
  (is (= "Nothing" (buddy 2382 3679)))
  (is (= "(9504 20735)" (buddy 8983 13355)))
  (is (= "(62744 75495)" (buddy 57345 90061))))

(deftest get-buddy-test
  (is (= '(48 75) (get-buddy 48)))
  (is (= '(9504 20735) (get-buddy 9504)))
  (is (= '(62744 75495) (get-buddy 62744))))

(deftest proper-divisors-test
  (is (= '(1 2 4 5 10 20 25 50) (sort (proper-divisors 100)))))
