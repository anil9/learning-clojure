(ns learning-clojure.mini-max-sum)
;; https://www.hackerrank.com/challenges/mini-max-sum/problem?isFullScreen=true
(defn miniMaxSum [arr]
  (let [sorted (sort arr)
        min-sum (reduce + (drop-last sorted))
        max-sum (reduce + (rest sorted))]
    (prn min-sum max-sum)))

(comment
  (miniMaxSum [1 2 3 4 5]))

