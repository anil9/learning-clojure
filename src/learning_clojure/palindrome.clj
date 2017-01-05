(ns learning-clojure.palindrome)
(require '[clojure.test :as test :refer :all])
;; palindrome
(defn palindrome [x]
  (= (seq x) (reverse (seq x))))

(deftest simple-palindrome
  (is (true? (palindrome "aba")))
  (is (false? (palindrome "abaa")))
  (is (true? (palindrome '(1 2 3 2 1))))
  (is (false? (palindrome '(1 2 3 4 5 6 7)))))

(run-tests)