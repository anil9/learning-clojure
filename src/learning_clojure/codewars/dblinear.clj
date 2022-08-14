(ns learning-clojure.codewars.dblinear)
;;https://www.codewars.com/kata/5672682212c8ecf83e000050/train/clojure

(defn fun
  ([x]
   (if (instance? Number x)
    [(inc (* 2 x)) (inc (* 3 x))]
    (mapcat fun x))))

(def inf-dblinear
  (flatten (iterate fun 1)))

(defn dblinear [n]
  (last (take (inc n) (dedupe (sort (take (* 10 (inc n)) inf-dblinear))))))
