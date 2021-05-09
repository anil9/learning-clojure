(ns learning-clojure.codewars.scrambles-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.scrambles :refer [scramble]]))

(deftest true-scrambles-test
  (is (true? (scramble "rkqodlw" "world")))
  (is (true? (scramble "cedewaraaossoqqyt" "codewars")))
  (is (true? (scramble "sammoc" "commas")))
  (is (true? (scramble "scriptingjava" "javascript")))
  (is (true? (scramble "scriptsjava" "javascripts")))
  (is (true? (scramble "aabbcamaomsccdd" "commas")))
  (is (true? (scramble "commas" "commas"))))

(deftest false-scrambles-test
  (is (false? (scramble "javscripts" "javascript")))
  (is (false? (scramble "scriptjavx" "javascript")))
  (is (false? (scramble "katas" "steak"))))