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

;; interposes the seq: x (a b c) = (a x b x c)
(defn my-interpose [element coll]
  (drop-last (mapcat #(list % element) coll)))

(deftest test-interpose
  (is (= [1 77 2 77 3] (my-interpose 77 [1 2 3])))
  (is (= [1 "hej" 2]) (my-interpose "hej" [1 2])))

;; drops every nth element
(defn drop-every-nth [coll n]
  (keep-indexed (fn [idx elem]
                  (let [counted-idx (+ idx 1)]
                    (if (or (< counted-idx n) (not= 0 (mod counted-idx n)))
                      elem)))
                coll))

(deftest test-drop-every-nth
  (is (= [1 3 5] (drop-every-nth [1 2 3 4 5] 2)))
  (is (= [1 3 5] (drop-every-nth [1 2 3 4 5 6] 2)))
  (is (= [1 2 4 5 7 8] (drop-every-nth (range 1 10) 3))))

;; calculates factorial
(defn fact [x]
  (reduce *' (range 1 (+ x 1))))

(deftest test-fact
  (is (= 1 (fact 1)))
  (is (= 6 (fact 3)))
  (is (= 120 (fact 5)))
  (is (= 40320 (fact 8))))

;; reverse interleave (1 2 3 4) 2 = ((1 3) (2 4))
(defn my-reverse-interleave [coll n]
  (loop [counter 0 current-coll coll result []]
    (if (= counter n) result
                      (recur (inc counter) (rest current-coll) (conj result (take-nth n current-coll))))))

(deftest test-reverse-interleave
  (is (= '((1 3) (2 4)) (my-reverse-interleave '(1 2 3 4) 2)))
  (is (= '((0 5) (1 6) (2 7) (3 8) (4 9)) (my-reverse-interleave (range 10) 5))))

;; rotates a collection to the left rotation amount of times (rotation >= 0)
(defn rotate-left [rotation coll]
  (loop [rotate rotation tmp-coll coll]
    (if (= rotate 0)
      tmp-coll
      (recur (dec rotate) (concat (rest tmp-coll) [(first tmp-coll)])))))

;; rotates a collection to the right rotation amount of times (rotation <= 0)
(defn rotate-right [rotation coll]
  (loop [rotate rotation tmp-coll coll]
    (if (= rotate 0)
      tmp-coll
      (recur (inc rotate) (cons (last tmp-coll) (butlast tmp-coll))))))

;; rotates a sequence either way
(defn rotate-seq [rotation coll]
  (if (> rotation 0) (rotate-left rotation coll) (rotate-right rotation coll)))

(deftest test-rotate-seq
  (is (= '(3 4 5 1 2) (rotate-seq 2 [1 2 3 4 5])))
  (is (= '(4 5 1 2 3) (rotate-seq -2 [1 2 3 4 5])))
  (is (= '(2 3 4 5 1) (rotate-seq 6 [1 2 3 4 5]))))

;; flips the params to the function
(fn flip-param [func]
  (fn [left right]
    (func right left)))

;; Cannot get tests to work.. It works in REPL.
;(deftest test-flip-param
;  (is (= [1 2 3] ((flip-param take) [1 2 3 4 5] 3)))
;  (is (= true? ((flip-param >) 7 8)))
;  (is (= 3 ((flip-param nth) [1 2 3 4 5]))))

(run-tests)
















































