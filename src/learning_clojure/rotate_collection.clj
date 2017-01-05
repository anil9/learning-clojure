(ns learning-clojure.rotate-collection)
(require '[clojure.test :as test :refer :all])

;; rotates a collection to the left rotation amount of times (rotation >= 0)
(defn- rotate-left [rotation coll]
  (loop [rotate rotation tmp-coll coll]
    (if (= rotate 0)
      tmp-coll
      (recur (dec rotate) (concat (rest tmp-coll) [(first tmp-coll)])))))

;; rotates a collection to the right rotation amount of times (rotation <= 0)
(defn- rotate-right [rotation coll]
  (loop [rotate rotation tmp-coll coll]
    (if (= rotate 0)
      tmp-coll
      (recur (inc rotate) (cons (last tmp-coll) (butlast tmp-coll))))))

;; rotates a sequence either way (positive rotation means right, negative rotation means left)
(defn rotate-coll [rotation coll]
  (if (> rotation 0) (rotate-left rotation coll) (rotate-right rotation coll)))

(deftest test-rotate-seq
  (is (= '(3 4 5 1 2) (rotate-coll 2 [1 2 3 4 5])))
  (is (= '(4 5 1 2 3) (rotate-coll -2 [1 2 3 4 5])))
  (is (= '(2 3 4 5 1) (rotate-coll 6 [1 2 3 4 5]))))
