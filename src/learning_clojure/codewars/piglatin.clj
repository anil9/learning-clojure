(ns learning-clojure.codewars.piglatin 
  (:require [clojure.string :as s]))
;https://www.codewars.com/kata/520b9d2ad5c005041100000f/train/clojure

(def punctuation #{"." "!" "?"})

(defn pig-it [s]
  (->> (re-seq #"\w+|\.|!|\?" s)
       (map #(if (contains? punctuation %)
               %
               (apply str (concat (rest %) (list (first %) "ay")))))
       (#(s/join " " %))))

(comment
  (re-seq #"\w+" "Pig latin")
  (apply str (conj (rest "Pig") \P))
  (first "Pig")
  (pig-it "Pig latin is cool"))
  
