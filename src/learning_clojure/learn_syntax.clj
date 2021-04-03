(ns learning-clojure.learn-syntax)
(require '[clojure.test :as test :refer :all])
(import java.net.URL)

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

(defn is-only-even?
  ([] false)
  ([& x] (= (count x) (count (filter even? x)))))

(deftest is-only-even-test
  (is (= true (is-only-even? 2 4 6)))
  (is (= false (is-only-even? 1 4 6)))
  (is (= false (is-only-even? 1 3 5)))
  (is (= true (is-only-even? 2))))

(defn opposite [f]
  (fn [& args] (not (apply f args))))

(deftest opposite-test
  (is (= true ((opposite is-only-even?) 3 4 3)))
  (is (= true ((opposite is-only-even?) 3)))
  (is (= true ((opposite is-only-even?))))
  (is (= false ((opposite is-only-even?) 2)))
  (is (= false ((opposite is-only-even?) 2 4 6))))

(defn http-get-java [url]
  (slurp (.openStream (URL. url))))

(deftest http-get-java-test
  (is (.contains (http-get-java "https://www.w3.org") "html"))
  (is (.contains (http-get-java "https://github.com") "login")))


(defn http-get [url]
  (slurp url))

(deftest http-get-test
  (is (.contains (http-get "https://www.w3.org") "html"))
  (is (.contains (http-get "https://github.com") "login")))

(defn one-less-arg [f x]
  (fn [& args] (apply f x args)))

(deftest one-less-arg-test
  (is (= true ((one-less-arg is-only-even? 2) 4))) 
  (is (= false ((one-less-arg is-only-even? 2) 3)))
  (is (= false ((one-less-arg is-only-even? 3) 2)))
  (is (= true ((one-less-arg is-only-even? 2) 2 4 6 8))))
