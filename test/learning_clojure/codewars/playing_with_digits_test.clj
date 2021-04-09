(ns learning-clojure.codewars.playing-with-digits-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.playing-with-digits :refer [dig-pow]])
  (:require [learning-clojure.codewars.playing-with-digits :refer [digits]])
  (:require [learning-clojure.codewars.playing-with-digits :refer [inc-pow]])
  (:require [learning-clojure.codewars.playing-with-digits :refer [sum-dig-pow]])
  )

(deftest dig-pow-test
  (is (= (dig-pow 89 1) 1))
  (is (= (dig-pow 92 1) -1))
  (is (= (dig-pow 135 1) 1))
  )

(deftest digits-test
  (is (= [4 5] (digits 45)))
  (is (= [1 2 3] (digits 123)))
  (is (= [1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9] (digits 123456789123456789)))
  (is (= [1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9] (digits 123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789)))

  )

(deftest inc-pow-test
  (is (= '(1 3 16) (map-indexed (partial inc-pow 0) '(2 3 4))))
  (is (= '(2 9 64) (map-indexed (partial inc-pow 1) '(2 3 4))))
  (is (= '(4 27 256) (map-indexed (partial inc-pow 2) '(2 3 4))))
  (is (= '(5764801 134217728 3486784401) (map-indexed (partial inc-pow 8) '(7 8 9))))

  )

(deftest sum-dig-pow-test
  (is (= (range 1 10) (sum-dig-pow 1 10)))
  (is (= (concat (range 1 10) '(89)) (sum-dig-pow 1 100)))
  (is (= '(89) (sum-dig-pow 10 100)))
  (is (= '() (sum-dig-pow 90 100)))
  (is (= '(135) (sum-dig-pow 90 150)))
  (is (= '(89 135) (sum-dig-pow 50 150)))
  (is (= '(89 135) (sum-dig-pow 10 150)))
  )
