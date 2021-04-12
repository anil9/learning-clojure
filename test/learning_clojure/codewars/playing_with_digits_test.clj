(ns learning-clojure.codewars.playing-with-digits-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.playing-with-digits :refer [dig-pow digits
                                                                   inc-pow
                                                                   sum-dig-pow
                                                                   power-sum-dig-term
                                                                   is-valid?
                                                                   prime?]]))


(deftest dig-pow-test
  (is (= (dig-pow 89 1) 1))
  (is (= (dig-pow 92 1) -1))
  (is (= (dig-pow 135 1) 1)))


(deftest digits-test
  (is (= [4 5] (digits 45)))
  (is (= [1 2 3] (digits 123)))
  (is (= [1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9] (digits 123456789123456789)))
  (is (= [1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9] (digits 123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789))))



(deftest inc-pow-test
  (is (= '(1 3 16) (map-indexed (partial inc-pow 0) '(2 3 4))))
  (is (= '(2 9 64) (map-indexed (partial inc-pow 1) '(2 3 4))))
  (is (= '(4 27 256) (map-indexed (partial inc-pow 2) '(2 3 4))))
  (is (= '(5764801 134217728 3486784401) (map-indexed (partial inc-pow 8) '(7 8 9)))))



(deftest sum-dig-pow-test
  (is (= (range 1 10) (sum-dig-pow 1 10)))
  (is (= (concat (range 1 10) '(89)) (sum-dig-pow 1 100)))
  (is (= '(89) (sum-dig-pow 10 100)))
  (is (= '() (sum-dig-pow 90 100)))
  (is (= '(135) (sum-dig-pow 90 150)))
  (is (= '(89 135) (sum-dig-pow 50 150)))
  (is (= '(89 135) (sum-dig-pow 10 150))))


(deftest power-sum-dig-term-test
  (is (= 81 (power-sum-dig-term 1)))
  (is (= 512 (power-sum-dig-term 2)))
  (is (= 2401 (power-sum-dig-term 3)))
  (is (= 4913 (power-sum-dig-term 4)))
  (is (= 614656 (power-sum-dig-term 10)))
  (is (= 27512614111 (power-sum-dig-term 21)))
  (is (= 52523350144 (power-sum-dig-term 22)))
  (is (= 52523350144 (power-sum-dig-term 22)))
  (is (= 68719476736 (power-sum-dig-term 23)))
  (is (= 248155780267521 (power-sum-dig-term 30))))


(deftest is-valid?-test
  (is (= true (is-valid? 81)))
  (is (= false (is-valid? 82)))
  (is (= true (is-valid? 512)))
  (is (= true (is-valid? 2401)))
  (is (= true (is-valid? 4913)))
  (is (= true (is-valid? 5832)))
  (is (= true (is-valid? 17576)))
  (is (= true (is-valid? 19683)))
  (is (= true (is-valid? 234256)))
  (is (= false (is-valid? 234257))))
  ;(is (= true (is-valid? 150094635296999121))))

(deftest prime?-test
  (is (= true (prime? 3)))
  (is (= false (prime? 4)))
  (is (= true (prime? 11))))

