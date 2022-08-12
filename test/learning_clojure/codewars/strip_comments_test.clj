(ns learning-clojure.codewars.strip-comments-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.strip-comments
             :refer [strip-comments]]))

(deftest strip-comments-test)
(are [text symbols expected] (= (strip-comments text symbols) expected)
    "apples, pears # and bananas\ngrapes\nbananas !apples"
    '("#" "!")
    "apples, pears\ngrapes\nbananas"
    
    "a #b\nc\nd $e f g"
    '("#" "$")
    "a\nc\nd")

