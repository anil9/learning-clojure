(ns learning-clojure.get-the-caps)
(require '[clojure.test :as test :refer :all])

;; Checks if a char is in upper case (CAPS)
(defn is-caps? [x]
  (= (str x) (clojure.string/upper-case x)))

(deftest test-caps
  (is (true? (is-caps? "A")))
  (is (false? (is-caps? "a")))
  (is (true? (is-caps? "ABCD")))
  (is (false? (is-caps? "abcd"))))


(defn whitespace? [c]
  (= " " (str c)))

(deftest test-whitespace
  (is (true? (whitespace? " ")))
  (is (false? (whitespace? "B"))))

;; takes a string and returns a new string containing only the capital letters
(defn get-the-caps [x]
  (apply str (remove whitespace? (filter is-caps? (map char x)))))

(deftest test-get-the-caps
  (is (= "YOLO" (get-the-caps "You Only Live Once"))))

(run-tests)