(ns learning-clojure.codingame.temperatures.temperatures)

; Write a program that prints the temperatures closest to 0 among input data.
; If two numbers are equally close to zero, positive integer has to be considered closest to zero


(defn closest-to-zero [numbers]
  (if (empty? numbers) 0
      (let [positives (filter pos? numbers)
            negatives (filter neg? numbers)
            closest-pos (when (not-empty positives) (apply min positives))
            closest-neg (when (not-empty negatives) (apply max negatives))]
        (if (every? some? [closest-pos closest-neg])
          (case (compare (abs closest-neg) closest-pos)
            -1 closest-neg
            closest-pos))
        (first (filter some? [closest-pos closest-neg])))))

(comment
  (closest-to-zero [-2 -1])
  (closest-to-zero [-2 2 3]))
