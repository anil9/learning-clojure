(ns learning-clojure.codewars.piglatin-test
  (:require [clojure.test :refer :all]
            [learning-clojure.codewars.piglatin :refer [pig-it]]))

(deftest pig-it-test
  (is (=  "igPay atinlay siay oolcay" (pig-it "Pig latin is cool")))
  (is (=   "elloHay orldway !" (pig-it "Hello world !"))))
