(ns learning-clojure.advent-of-code.2021.day2-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.advent-of-code.2021.day2 :refer :all]))

(deftest horizontal-position-test
  (is (= 2 (horizontal-position '(["forward" "2"]))))
  (is (= 3 (horizontal-position '(["forward" "1"] ["forward" "2"]))))
  (is (= 4 (horizontal-position '(["forward" "2"] ["forward" "2"]))))
  (is (= 4 (horizontal-position '(["forward" "2"] ["forward" "2"] ["down" "1"]))))
  (is (= 4 (horizontal-position '(["forward" "2"] ["forward" "2"] ["up" "1"]))))
  (is (= 4 (horizontal-position '(["forward" "2"] ["forward" "2"] ["up" "1"] ["down" "2"])))))


(deftest vertical-position-test
  (is (= 0 (vertical-position '(["forward" "2"]))))
  (is (= 0 (vertical-position '(["forward" "1"] ["forward" "2"]))))
  (is (= 0 (vertical-position '(["forward" "2"] ["forward" "2"]))))
  (is (= 1 (vertical-position '(["forward" "2"] ["forward" "2"] ["down" "1"]))))
  (is (= -1 (vertical-position '(["forward" "2"] ["forward" "2"] ["up" "1"]))))
  (is (= 1 (vertical-position '(["forward" "2"] ["forward" "2"] ["up" "1"] ["down" "2"])))))

