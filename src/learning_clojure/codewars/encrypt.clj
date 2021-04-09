(ns learning-clojure.codewars.encrypt
  (:require
    [clojure.string :as str]))

(defn seconds [st]
  (if (< (count st) 2)
    ""
    (apply str (second st) (seconds (drop 2 st)))))

(defn non-seconds [st]
  (cond
    (empty? st) ""
    (<= (count st) 2) (str (first st))
    :else (apply str (first st) (non-seconds (drop 2 st)))))

(defn encrypt [st n]
  (if (or (str/blank? st) (<= n 0))
    st
    (encrypt (apply str (seconds st) (non-seconds st)) (dec n))))

(defn two-part-str [st]
  (let [st st
        half-len (quot (count st) 2)]
    (list (subs st 0 half-len) (subs st half-len))))

(defn decrypt [st n]
  (if (or (str/blank? st) (<= n 0))
    st
    (let [first-half (first (two-part-str st))
          second-half (second (two-part-str st))]
      (if (= (count first-half) (count second-half))
        (decrypt (apply str (interleave second-half first-half)) (dec n))
        (decrypt (apply str (concat (interleave second-half first-half) [(last second-half)])) (dec n))))))