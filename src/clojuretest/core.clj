(ns clojuretest.core)
   
(ns clojure.test.example  (:use clojure.test))

(defn -main []
  (println "Hello, World!"))

;; fibonacci
(defn fib [x]
  (map first (take x (iterate (fn [[a b]] [b (+' a b)]) [1 1]))))
  
(deftest fibtest
  (is (= [1 1] (fib 2)))
  (is (= [1 1 2 3 5] (fib 5))))


;; palindrome
(defn palindrome [x]
  (= (seq x) (reverse (seq x))))

(deftest simple-palindrome
  (is(true? (palindrome "aba")))
  (is(false? (palindrome "abaa")))
  (is(true? (palindrome '(1 2 3 2 1))))
  (is(false? (palindrome '(1 2 3 4 5 6 7)))))

;; flatten
(defn flattens [x]
  (seq x))


(run-all-tests)
















































