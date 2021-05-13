(ns learning-clojure.codewars.gap-in-primes-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.gap-in-primes :refer [gap]]))

(deftest gap-test
  (testing "Basic tests"
    (is (= [101, 103] (gap 2,100,110)))
    (is (= [103, 107] (gap 4,100,110)))
    (is (= nil (gap 6,100,110)))
    (is (= [359, 367] (gap 8,300,400)))
    (is (= [337, 347] (gap 10,300,400)))))
