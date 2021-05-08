(ns learning-clojure.codewars.sudoku-done-or-not)
(def sudoku-numbers (into #{} (range 1 10)))

(defn line-complete? [line]
  (true? (= sudoku-numbers (into #{} line))))

(defn transpose [board]
  (apply map list board))

(defn done-or-not [board]
  (let [x-axis-complete (->> board
                             (every? line-complete?))
        y-axis-complete (->> (transpose board)
                             (every? line-complete?))]
    (if (and x-axis-complete y-axis-complete)
      "Finished!"
      "Try again!")))

(done-or-not [[5 3 4 6 7 8 9 1 2]
              [6 7 2 1 9 5 3 4 8]
              [1 9 8 3 4 2 5 6 7]
              [8 5 9 7 6 1 4 2 3]
              [4 2 6 8 5 3 7 9 1]
              [7 1 3 9 2 4 8 5 6]
              [9 6 1 5 3 7 2 8 4]
              [2 8 7 4 1 9 6 3 5]
              [3 4 5 2 8 6 1 7 9]])
