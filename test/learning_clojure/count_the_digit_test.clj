(ns learning-clojure.count-the-digit-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.count-the-digit :refer [nb-dig]]))

(deftest nb-dig-test
  (is (= 4700 (nb-dig 5750 0)))
  (is (= 9481 (nb-dig 11011 2)))
  (is (= 7733 (nb-dig 12224 8)))
  (is (= 11905 (nb-dig 11549 1)))
  )
