(ns learning-clojure.codewars.comp-same)

(defn square [x]
  (* x x))

(defn compSame [a b]
  "Same means that elements in -b are the same as elements in -a squared, regardless of order"
  (if (or (nil? a) (nil? b))
    false
    (= (sort b) (sort (map square a)))))