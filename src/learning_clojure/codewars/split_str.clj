(ns learning-clojure.codewars.split-str)
;https://www.codewars.com/kata/515de9ae9dcfc28eb6000001/train/clojure

(defn solution [s]
  (vec (map #(apply str %) (partition 2 2 "_" s))))
