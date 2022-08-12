(ns learning-clojure.codewars.readability-is-king-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.readability-is-king
              :refer [text->words count-syllables flesch-kincaid]]))

(deftest words-per-sentence-test
  (is (= 4 (count (text->words "The turtle is leaving.")))))

(deftest num-syllables-test
  (is (= 2 (count-syllables "leaving")))
  (is (= 1 (count-syllables "hi")))
  (is (= 2 (count-syllables "bodyguard")))
  (is (= 1 (count-syllables "not")))
  (is (= 1 (count-syllables "be")))
  (is (= 1 (count-syllables "That"))))

  
(defn =? [a b] (= (format "%.2f" a) (format "%.2f" b)))

(def cases [ 
            [ "The turtle is leaving." 3.67]
            [ "A good book is hard to find." -1.06]
            [ "To be or not to be. That is the question." -0.66]
            [ "Oh no! The lemming is falling." 1.31]
            [ "Do not cut your fingers as your katana is getting sharper! Be gentle." 4.19]])
   
(deftest deterministic-tests
  (doseq [[text val] cases]
         (testing text (is (=? (flesch-kincaid text) val)))))
