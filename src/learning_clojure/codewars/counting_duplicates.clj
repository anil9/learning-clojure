(ns learning-clojure.codewars.counting-duplicates)

(defn duplicate-count [text]
  (let [freq (frequencies (to-array (clojure.string/lower-case text)))]
    (prn freq)
    (count
      (->> (keys freq)
           (filter #(< 1 (get freq %)))))))