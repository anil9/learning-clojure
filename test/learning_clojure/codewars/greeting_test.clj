(ns learning-clojure.codewars.greeting-test
  (:require [clojure.test :refer :all]
            [learning-clojure.codewars.greeting :refer [greet]]))

(deftest greet-test
  (is (= (greet "johan") "Hello, johan how are you doing today?"))
  (is (= (greet "testName") "Hello, testName how are you doing today?")))
