(ns learning-clojure.core)
(require '[clojure.test :as test :refer :all])

;; calculates factorial
(defn fact [x]
  (reduce *' (range 1 (+ x 1))))

(deftest test-fact
  (is (= 1 (fact 1)))
  (is (= 6 (fact 3)))
  (is (= 120 (fact 5)))
  (is (= 40320 (fact 8))))

;; interleaves the seqs (a b c) (x y z) = (a x b y c z)
(defn my-interleave [x y]
  (loop [a x b y result []]
    (if (and (seq a) (seq b))
      (recur (rest a) (rest b) (conj result (first a) (first b)))
      result)))

(deftest test-interleave
  (is (= '(1 4 2 5 3 6) (my-interleave '(1 2 3) '(4 5 6))))
  (is (= "apbqcrdset" (apply str (my-interleave "abcde" "pqrst")))))


;; reverse interleave (1 2 3 4) 2 = ((1 3) (2 4))
(defn my-reverse-interleave [coll n]
  (loop [counter 0 current-coll coll result []]
    (if (= counter n) result
                      (recur (inc counter) (rest current-coll) (conj result (take-nth n current-coll))))))

(deftest test-reverse-interleave
  (is (= '((1 3) (2 4)) (my-reverse-interleave '(1 2 3 4) 2)))
  (is (= '((0 5) (1 6) (2 7) (3 8) (4 9)) (my-reverse-interleave (range 10) 5))))

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

;; split a collection into equal sized parts
(defn split-coll [size coll]
  (loop [idx 0 first-part [] second-part coll]
    (if (= idx size)
      [first-part second-part]
      (recur (inc idx) (conj first-part (first second-part)) (rest second-part)))))

(deftest test-split-coll
  (is (= [[1 2 3] [4 5 6]] (split-coll 3 [1 2 3 4 5 6])))
  (is (= [[[1 2] [3 4]] [[5 6]]] (split-coll 2 [[1 2] [3 4] [5 6]]))))


;; Compress a sequence: removes consecutive duplicates from a sequence.
(defn compress [x]
  (apply str (dedupe x)))

(deftest test-compress
  (is (= "YOLO" (compress "YYYOOOLLLLOOOOOOOOOO")))
  (is (= "test" (compress "test"))))

;; flatten
(defn my-flatten [x]
  (if (sequential? x)
    (mapcat my-flatten x)
    (list x)))

(deftest test-flattens
  (is (= [1 2 3] (my-flatten [1 [2 3]])))
  (is (= [1 2 3 4 4] (my-flatten [1 '(2 3 4 [4])]))))

; Replicates a sequence n times
(defn my-replicate [coll n]
  (mapcat #(repeat n %) coll))

(deftest test-replicate
  (is (= '(1 1 1 2 2 2 3 3 3) (my-replicate '(1 2 3) 3))))

; duplication using my-replicate
(defn duplicate [coll]
  (my-replicate coll 2))

(deftest test-duplicate
  (is (= '(1 1 2 2 3 3) (duplicate '(1 2 3))))
  (is (= '([1 2] [1 2] [3 4] [3 4]) (duplicate '([1 2] [3 4])))))

;; Implements the range function
(defn my-range [from exclusive-to]
  (loop [range-list [] counter from]
    (if (< counter exclusive-to)
      (recur (conj range-list counter) (inc counter))
      range-list)))

(deftest test-range
  (is (= (range 1 4) (my-range 1 4)))
  (is (= '(7 8 9 10)) (my-range 7 11))
  (is (= (range 0 10000) (my-range 0 10000))))

;; returns the max value from args
(defn maximum [& args]
  (reduce #(if (> %1 %2) %1 %2)
          args))

(deftest test-maximum
  (is (= 22 (maximum 1 3 5 7 11 13 17 21 22)))
  (is (= 1 (maximum -1 0 0 0 0 0 1 0 0 0 0 -2)))
  (is (= 99 (apply maximum (my-range -100 100)))))

;; finds all divisors of x
(defn proper-divisors [x]
  (->> (range 1 x)
       (filter #(= (mod x %) 0))))

(deftest test-divisors
  (is (= '(1 2 4 71 142) (proper-divisors 284)))
  (is (= '(1 2 4 5 10 11 20 22 44 55 110) (proper-divisors 220)))
  (is (= '(1) (proper-divisors 7)))
  (is (= '(1) (proper-divisors 13)))
  (is (= '(1) (proper-divisors 31))))

;; multiplies x over the list
(defn mul-over-list [list x]
  (map #(* x %) list))

(deftest test-mul-list
  (is (= '(2 4 6) (mul-over-list '(1 2 3) 2))))

;; flips the params to the function
;(fn flip-param [func]
;  (fn [left right]
;    (func right left)))
;
;(deftest test-flip-param
; (is (= [1 2 3] ((flip-param take) [1 2 3 4 5] 3)))
; (is (= true? ((flip-param >) 7 8)))
; (is (= 3 ((flip-param nth) [1 2 3 4 5]))))



(run-tests)
