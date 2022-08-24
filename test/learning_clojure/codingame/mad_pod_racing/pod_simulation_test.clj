(ns learning-clojure.codingame.mad-pod-racing.pod-simulation-test
  (:require [clojure.test :refer :all]
            [learning-clojure.codingame.mad-pod-racing.pod-simulation :refer :all]))

(deftest move-test
  (let [pod {:vx 1 :vy 2 :point [0 0]}]
    (is (= {:vx 1 :vy 2 :point [1 2]} (move pod)))
    (is (= {:vx 1 :vy 2 :point [2 4]} (last (take 3 (iterate move pod)))))
    (is (= {:vx 1 :vy 2 :point [3 6]} (last (take 4 (iterate move pod)))))))

(deftest angle-test
  (is (= 315.0 (angle [0 9000] [9000 0]))))


(deftest reached-target-test
  (is (true? (reached-target? {:pod {:point [100 100]}
                               :target {:point [90 90]
                                        :radius 50}})))
  (is (false? (reached-target? {:pod {:point [100 100]}
                                :target {:point [300 90]
                                         :radius 50}}))))
