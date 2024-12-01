(ns learning-clojure.advent-of-code.2021.day4
  (:require [learning-clojure.advent-of-code.2021.input-reader :as input-reader]
            [clojure.string :as str]))

(def day "day4")
(def part1-example-file (str day "/example-input-part1"))
(def part1-file (str day "/input-part1"))
(def part2-example-file (str day "/example-input-part2"))
(def part2-file (str day "/input-part2"))

(defn raw-input [] (input-reader/raw-input part1-file))
(defn input [] (input-reader/get-line-separated-input part1-file))
(defn numbers-to-draw []
  (->> (str/split (first input) #",")
       (map #(Integer/parseInt %))))



(defn remove-empty [coll]
    (->> coll
         (remove empty?)))

(defn to-int [coll]
  (->> coll
       (map #(Integer/parseInt %))))
;(mapv #(str/split % #" ") (rest input))
(defn into-row [row-str]
  (->> row-str
       (map #(str/split % #" "))
       (map remove-empty)
       (map to-int)
       (map vec)))


(defn boards []
  (map vec
       (->> (rest (str/split (raw-input) #"\n\n"))
            (map #(str/split % #"\n"))
            (map into-row))))

(defn in?
  "true if coll contains elm"
  [coll elm]
  (some #(= elm %) coll))

(defn bingo-row? [row drawn-numbers]
  (every? #(in? drawn-numbers %) row))

;(prn numbers-to-draw)
;(prn (first numbers-to-draw))
;(prn  (into #{} (take 1 numbers-to-draw)))
;(prn (first boards)
;(prn (bingo-row? (first (first boards)) (take 20 numbers-to-draw)))

(defn transpose [board]
  (vec
    (map vec (apply map list board))))

(defn bingo-board? [board drawn-numbers]
  (let [rows board
        cols (transpose board)]
    (some #(bingo-row? % drawn-numbers) (vec (concat rows cols)))))

(defn sum-unmarked [board drawn-numbers]
  (apply +
         (->> (flatten board)
              (remove #(in? drawn-numbers %)))))

(defn part1 [boards numbers-to-draw]
  (loop [num-drawn 1]
    (let [drawn-numbers (into #{} (take num-drawn numbers-to-draw))
          winner-board (first (->> boards
                                   (filter #(bingo-board? % drawn-numbers))))]
      (if (not (nil? winner-board))
        (* (sum-unmarked winner-board drawn-numbers)
           (nth numbers-to-draw (dec num-drawn)))
        (recur (inc num-drawn))))))

;(prn (part1 boards numbers-to-draw))


(defn part2 [boards numbers-to-draw]
  (loop [num-drawn 1
         still-loosing-boards boards]
    (let [drawn-numbers (into #{} (take num-drawn numbers-to-draw))
          loosing-boards (if (= 1 (count still-loosing-boards))
                           still-loosing-boards
                           (->> still-loosing-boards
                                (remove #(bingo-board? % drawn-numbers))))
          winner-board (first (->> loosing-boards
                                   (filter #(bingo-board? % drawn-numbers))))]
      (if (and (<= 0 (count loosing-boards)) (not (nil? winner-board)))
        (* (sum-unmarked (first loosing-boards) drawn-numbers)
           (nth numbers-to-draw (dec num-drawn)))
        (recur (inc num-drawn) loosing-boards)))))

(comment
  (part2 (boards) numbers-to-draw))
;(prn (part2 boards numbers-to-draw))
