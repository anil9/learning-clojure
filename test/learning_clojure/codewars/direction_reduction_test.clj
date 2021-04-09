(ns learning-clojure.codewars.direction-reduction-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.direction-reduction :refer [dirReduc]]))

(deftest dirReduc-test
  (is (= ["WEST"] (dirReduc ["NORTH" "SOUTH" "SOUTH" "EAST" "WEST" "NORTH" "WEST"])))
  (is (= nil (dirReduc ["NORTH" "SOUTH" "EAST" "WEST"]))))

