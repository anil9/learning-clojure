(ns learning-clojure.codingame.shadows-of-the-knight.ep1-test
  (:require [clojure.test :refer :all]
            [learning-clojure.codingame.shadows-of-the-knight.ep1 :refer :all]))


(deftest translate-test
  (is (= [1 0] (translate "R")))
  (is (= [1 1] (translate "DR")))
  (is (= [-1 -1] (translate "UL")))
  (is (= [0 1] (translate "D")))
  (is (= [0 -1] (translate "U")))
  (is (= [-1 0] (translate "L")))
  (is (= [1 -1] (translate "UR")))
  (is (= [-1 1] (translate "DL"))))

(def sample-rec
  {:nw [0 0] :ne [8 0]
   :sw [0 8] :se [8 8]})
(def another-rec
  {:nw [3 0] :ne [9 0]
   :sw [3 4], :se [9 4]})

(deftest apply-direction-test
  (is (= {:nw [0 6] :ne [4 6]
          :sw [0 8] :se [4 8]}
         (apply-direction [5 5] "DL" sample-rec)))
  (is (= {:nw [0 0] :ne [4 0]
          :sw [0 4] :se [4 4]}
         (apply-direction [5 5] "UL" sample-rec)))
  (is (= {:nw [6 0] :ne [8 0]
          :sw [6 4] :se [8 4]}
         (apply-direction [5 5] "UR" sample-rec)))
  (is (= {:nw [6 6] :ne [8 6]
          :sw [6 8] :se [8 8]}
         (apply-direction [5 5] "DR" sample-rec)))
  (is (= {:nw [5 6] :ne [5 6]
          :sw [5 8] :se [5 8]}
         (apply-direction [5 5] "D" sample-rec)))
  (is (= {:nw [5 0] :ne [5 0]
          :sw [5 4] :se [5 4]}
         (apply-direction [5 5] "U" sample-rec)))
  (is (= {:nw [0 5] :ne [4 5]
          :sw [0 5] :se [4 5]}
         (apply-direction [5 5] "L" sample-rec)))
  (is (= {:nw [6 5] :ne [8 5]
          :sw [6 5] :se [8 5]}
         (apply-direction [5 5] "R" sample-rec)))
  (is (= {:nw [7 3] :ne [9 3]
          :sw [7 4] :se [9 4]}
         (apply-direction [6 2] "DR" another-rec))))


(deftest center-coord-test
  (is (= [4 4] (center-coord {:nw [0 0]
                              :ne [8 0]
                              :sw [0 8]
                              :se [8 8]}))))
