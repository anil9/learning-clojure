(ns learning-clojure.core (:use clojure.test))

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
  (if (sequential? x)
    (mapcat flattens x)
    (list x)))

(deftest test-flattens
  (is(=[1 2 3] (flattens [1 [2 3]])))
  (is(=[1 2 3 4 4] (flattens [1 '(2 3 4 [4])]))))


;; Checks if a char is in caps
(defn is-caps? [x]
  (= (str x) (clojure.string/upper-case x)))

(deftest test-caps
  (is(true? (is-caps? "A")))
  (is(false? (is-caps? "a")))
  (is (true? (is-caps? "ABCD")))
  (is (false? (is-caps? "abcd"))))

(defn whitespace? [c]
  (re-matches #" " (str c)))


;; takes a string and returns a new string containing only the capital letters
(defn get-the-caps [x]
    (apply str(remove whitespace? (filter is-caps? (map char x)))))

(deftest test-get-the-caps
  (is(="YOLO" (get-the-caps "You Only Live Once"))))

;; Compress a sequence: removes consecutive duplicates from a sequence.
(defn compress [x]
  (apply str (dedupe x)))

(deftest test-compress
  (is (="YOLO" (compress "YYYOOOLLLLOOOOOOOOOO")))
  (is (="test" (compress "test"))))

;; Duplicate a sequence
(defn duplicate [x]
  (mapcat #(repeat 2 %) x))

(deftest test-duplicate
  (is (= '(1 1 2 2 3 3) (duplicate '(1 2 3))))
  (is (= '([1 2] [1 2] [3 4] [3 4]) (duplicate '([1 2] [3 4])))))


(run-tests)
















































