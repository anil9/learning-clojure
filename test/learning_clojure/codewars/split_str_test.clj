(ns learning-clojure.codewars.split-str-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.split-str :refer [solution]]))

(deftest solution-test
  (is (= ["cd" "ab" "ef" "g_"] (solution "cdabefg")))
  (is (= ["cd" "ab" "ef" "gh"] (solution "cdabefgh")))
  (is (= ["ab" "cd"] (solution "abcd"))))
