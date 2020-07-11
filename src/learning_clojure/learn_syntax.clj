(ns learning-clojure.learn-syntax)
(require '[clojure.test :as test :refer :all])
(+ 7654 1234)

(/ (+ 7 (* 3 4) 5) 10)

(defn greet [] 
  (println "Hello1"))

(def greet
  (fn[] (println "Hello2")))

(def greet
  #(println "Hello3"))
(greet)

(defn greeting
  ([] (str "Hello, World!"))
  ([who] (str "Hello, " who "!"))
  ([phrase who] (str phrase ", " who "!")))

(deftest greeting-test
  (assert (="Hello, World!" (greeting)))
  (assert (="Hello, Clojure!" (greeting "Clojure")))
  (assert (="Good morning, Clojure!" (greeting "Good morning" "Clojure"))))

(defn do-nothing [x] x)

(deftest do-nothing-test
  (assert (="hej" (do-nothing "hej")))
  (is (= 12/5 (do-nothing 12/5)))
  (is (= 2.51 (do-nothing 2.51))))

(defn always-thing [& _] 100)

(deftest always-thing-test
  (is (= 100 (always-thing)))
  (is (= 100 (always-thing "hej" 12/5)))
  (is (= 100 (always-thing '(2.51 "test")))))

(defn make-thingy [x]
  (fn[& _] x))

(deftest make-thingy-test
  (let [n 1337
        f (make-thingy n)]
    (is (= n (f)))
    (is (= n (f 123)))
    (is (= n (apply f 123 (range))))))
