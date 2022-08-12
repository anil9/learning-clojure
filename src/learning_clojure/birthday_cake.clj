(ns learning-clojure.birthday-cake)

;; https://www.hackerrank.com/challenges/birthday-cake-candles/problem?isFullScreen=true&h_r=next-challenge&h_v=zen
(defn birhday-cake-candles [candles]
  (let [highest-candle (apply max candles)]
    (get (frequencies candles) highest-candle)))

(comment
  (apply max [3 2 1 3])
  (get 3 (frequencies [3 2 1 3]))
  (birhday-cake-candles [3 2 1 3]))
