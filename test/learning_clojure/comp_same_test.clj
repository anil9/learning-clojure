(ns learning-clojure.comp-same-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.comp-same :refer [compSame]]))

(deftest compSame-test
  (is (= true (compSame [19 11] [121 361])))
  (is (= false (compSame [19 12] [121 361])))
  (is (= true (compSame [121, 144, 19, 161, 19, 144, 19, 11] [121, 14641, 20736, 361, 25921, 361, 20736, 361])))
  (is (= false (compSame nil [121, 14641, 20736, 361, 25921, 361, 20736, 361])))
  (is (= false (compSame nil nil)))
  (is (= false (compSame [19 11] [121 361 121])))

  )
