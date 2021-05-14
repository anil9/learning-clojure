(ns learning-clojure.codewars.backspaces)

(defn safe-pop [coll]
 (if (empty? coll) coll (pop coll)))

(defn clean-string [s]
  (loop [s s
         res '()]
    (let [c (first s)
          backspace? (= c \#)]
      (if (nil? c) (apply str (reverse res))
                   (recur (rest s) (if backspace? (safe-pop res)
                                                  (conj res c)))))))