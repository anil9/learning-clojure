(ns learning-clojure.codingame.power-of-thor.ep1-test
  (:require [clojure.test :refer :all]
            [learning-clojure.codingame.power-of-thor.ep1 :refer [next-move one-step translate-direction all-directions]]))


(deftest one-step-test
  (is (= 1 (one-step 4 6)))
  (is (= -1 (one-step 6 4)))
  (is (= 0 (one-step 1 1))))

(deftest next-move-test
  (is (= [1 1] (next-move [0 0] [1 1])))
  (is (= [0 1] (next-move [0 0] [0 1])))
  (is (= [1 0] (next-move [0 0] [1 0])))
  (is (= [1 1] (next-move [0 0] [3 8])))
  (is (= [-1 0] (next-move [18 17] [0 17])))
  (is (= [0 0] (next-move [2 2] [2 2])))
  (is (= [0 0] (next-move [3 3] [3 4]))))


(deftest translate-direction-test
  (is (= "S" (translate-direction [0 1])))
  (is (= "N" (translate-direction [0 -1])))
  (is (= "E" (translate-direction [1 0])))
  (is (= "W" (translate-direction [-1 0])))
  (is (= "SE" (translate-direction [1 1])))
  (is (= "SW" (translate-direction [-1 1])))
  (is (= "NE" (translate-direction [1 -1])))
  (is (= "NW" (translate-direction [-1 -1]))))


(deftest all-directions-test
  (is (= ["SE" "SE" "S"] (all-directions [1 1] [3 4]))))
