(ns learning-clojure.core
  (:use clojure.test))

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
  (is (true? (palindrome "aba")))
  (is (false? (palindrome "abaa")))
  (is (true? (palindrome '(1 2 3 2 1))))
  (is (false? (palindrome '(1 2 3 4 5 6 7)))))

;; flatten
(defn flattens [x]
  (if (sequential? x)
    (mapcat flattens x)
    (list x)))

(deftest test-flattens
  (is (= [1 2 3] (flattens [1 [2 3]])))
  (is (= [1 2 3 4 4] (flattens [1 '(2 3 4 [4])]))))


;; Checks if a char is in caps
(defn is-caps? [x]
  (= (str x) (clojure.string/upper-case x)))

(deftest test-caps
  (is (true? (is-caps? "A")))
  (is (false? (is-caps? "a")))
  (is (true? (is-caps? "ABCD")))
  (is (false? (is-caps? "abcd"))))

(defn whitespace? [c]
  (re-matches #" " (str c)))


;; takes a string and returns a new string containing only the capital letters
(defn get-the-caps [x]
  (apply str (remove whitespace? (filter is-caps? (map char x)))))

(deftest test-get-the-caps
  (is (= "YOLO" (get-the-caps "You Only Live Once"))))

;; Compress a sequence: removes consecutive duplicates from a sequence.
(defn compress [x]
  (apply str (dedupe x)))

(deftest test-compress
  (is (= "YOLO" (compress "YYYOOOLLLLOOOOOOOOOO")))
  (is (= "test" (compress "test"))))

;; Replicates a sequence
(defn my-replicate [coll n]
  (mapcat #(repeat n %) coll))

(defn duplicate [coll]
  (my-replicate coll 2))

(deftest test-duplicate
  (is (= '(1 1 2 2 3 3) (duplicate '(1 2 3))))
  (is (= '([1 2] [1 2] [3 4] [3 4]) (duplicate '([1 2] [3 4])))))
(deftest test-replicate
  (is (= '(1 1 1 2 2 2 3 3 3) (my-replicate '(1 2 3) 3))))


;; Implements the range function
(defn my-range [from exclusive-to]
  (loop [range-list [] counter from]
    (if (< counter exclusive-to)
      (recur (conj range-list counter) (inc counter))
      range-list)))

(deftest test-range
  (is (= '(1 2 3) (my-range 1 4)))
  (is (= '(7 8 9 10)) (my-range 7 11)))

;; returns the max value from args
(defn maximum [& args]
  (reduce #(if (> %1 %2) %1 %2) args))

(deftest test-maximum
  (is (= 22 (maximum 1 3 5 7 11 13 17 21 22)))
  (is (= 1 (maximum -1 0 0 0 0 0 1 0 0 0 0 -2))))

;; interleaves the seqs (a b c) (x y z) = (a x b y c z)
(defn my-interleave [x y]
  (loop [a x b y result []]
    (if (and (seq a) (seq b))
      (recur (rest a) (rest b) (conj result (first a) (first b)))
      result)))

(deftest test-interleave
  (is (= '(1 4 2 5 3 6) (my-interleave '(1 2 3) '(4 5 6)))))

(run-tests)
















































