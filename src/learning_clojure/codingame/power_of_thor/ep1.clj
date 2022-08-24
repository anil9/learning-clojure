(ns learning-clojure.codingame.power-of-thor.ep1)

(defn one-step [from to]
  (cond
    (< from to) 1
    (> from to) -1
    :else 0))

(comment
  (one-step 4 6)
  (one-step 6 4)
  (one-step 1 1))

(defn next-move [[x y] [tx ty]]
  [(one-step x tx) (one-step y ty)])

(defn north-south [y]
  (cond
    (< y 0) \N
    (> y 0) \S))

(defn east-west [x]
  (cond
    (< x 0) \W
    (> x 0) \E))

(defn translate-direction 
  "direction is a coordinate -1 <= x y <= 1 that translates into N NW E SE .. NW"
  [[x y]]
  (str (north-south y) (east-west x)))

(defn vec+ [v1 v2]
  (mapv + v1 v2))

(comment
  (vec+ [1 2] [2 3]))
  

(defn all-directions [from to]
  (loop [f from
         path []]
    (if (= f to) (map translate-direction path)
      (let [move (next-move f to)]
        (recur (vec+ f move) (conj path move))))))
(def values (atom nil))
(comment 
  (all-directions [1 1] [2 2]))
