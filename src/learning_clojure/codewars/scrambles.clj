(ns learning-clojure.codewars.scrambles)
(defn scramble [s1 s2]
  (let [s1_freq (frequencies s1)
        s2_freq (frequencies s2)]
    (->> s2_freq
         (every? (fn [[k v]]
                     (>= (get s1_freq k 0) v))))))