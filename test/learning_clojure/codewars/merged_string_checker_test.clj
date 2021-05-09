(ns learning-clojure.codewars.merged-string-checker-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.merged-string-checker :refer [is-merge]]))

(deftest valid-merges-test
  (is (is-merge "codewars" "code" "wars"))
  (is (is-merge "codewars" "cdw" "oears"))
  (is (is-merge "Bananas from Bahamas" "Bahas" "Bananas from am")))

(deftest invalid-merges-test
  (is (not (is-merge "codewars" "cod" "war")))
  (is (not (is-merge "codewars" "cod" "warqa"))))
