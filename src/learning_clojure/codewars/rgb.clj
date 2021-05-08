(ns learning-clojure.codewars.rgb)

(defn within-range [i]
  (cond
    (< i 0) 0
    (> i 255) 255
    :else i))

(defn rounded [i]
  (int (Math/round (double (within-range i)))))

(defn rgb [r g b]
  (let [r (rounded r)
        g (rounded g)
        b (rounded b)]
    (format "%02X%02X%02X" r g b)))