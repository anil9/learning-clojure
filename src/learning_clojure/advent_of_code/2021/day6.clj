(ns learning-clojure.advent-of-code.2021.day6
  (:require
    [learning-clojure.advent-of-code.2021.input-reader :as input-reader]
    [clojure.string :as str]
    [clojure.set :as sets]))
(def day "day6")
(def part1-example-file (str day "/example-input-part1"))
(def part1-file (str day "/input-part1"))
(def part2-example-file (str day "/example-input-part2"))
(def part2-file (str day "/input-part2"))

(def part1-data
  (->> (input-reader/split-line-input-at part1-file #",")
       (flatten)
       (map #(Long/parseLong %))))

(def part2-data
  (->> (input-reader/split-line-input-at part2-file #",")
       (flatten)
       (map #(Long/parseLong %))))


(defn should-reproduce? [fish]
  (= fish 0))

(defn new-fish []
  8)

(defn create-fish []
  [6 (new-fish)])
  ;{:old-fish 6
  ; :new-fish (new-fish)})

(defn process-day [fishes]
  (->> fishes
       (map #(if (should-reproduce? %) (create-fish) (dec %)))
       (flatten)))

(defn fishes-at-day [day data]
  (loop [i 0
         fishes data]
    (if (= i day)
      (count fishes)
      (recur (inc i) (process-day fishes)))))

(defn part1 []
  (fishes-at-day 80 part1-data))

; part 2 below

(def max-reprod-days 8)

(defn process-day-speedy [fishes]
  (let [new-fish (get fishes 0 0)
        fishes-updated (merge
                              (hash-map 8 new-fish)
                              (into {} (->> (range 1 (inc max-reprod-days))
                                            (map #(hash-map (dec %) (get fishes % 0))))))]
      (update fishes-updated 6 #(+ new-fish %))))


(defn fishes-at-day-speedy [day data]
  (loop [i 0
         fishes (frequencies data)]
    (if (= i day)
      (apply + (vals fishes))
      (do
        (recur (inc i) (process-day-speedy fishes))))))


(defn part2 []
  (fishes-at-day-speedy 256 part2-data))

(comment
  (part1)
  (part2))
