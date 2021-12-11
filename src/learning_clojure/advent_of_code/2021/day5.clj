(ns learning-clojure.advent-of-code.2021.day5
  (:require
    [learning-clojure.advent-of-code.2021.input-reader :as input-reader]
    [clojure.string :as str]
    [clojure.set :as sets]))

(def day "day5")
(def part1-example-file (str day "/example-input-part1"))
(def part1-file (str day "/input-part1"))
(def part2-example-file (str day "/example-input-part2"))
(def part2-file (str day "/input-part2"))

(defn to-int [coll]
  (->> coll
       (map #(Integer/parseInt %))))
(defn str-line-to-int [str-line]
  (->> str-line
       (map #(str/split % #","))
       (map #(to-int %))))

(def part1-lines
  (->> (input-reader/split-line-input-at part1-file #" -> ")
       (map #(str-line-to-int %))))
(def x_min
  (apply min (->> (flatten part1-lines)
                  (keep-indexed #(if (odd? %1) %2)))))
(def x_max
  (apply max (->> (flatten part1-lines)
                  (keep-indexed #(if (odd? %1) %2)))))
(def y_min
  (apply min (->> (flatten part1-lines)
                  (keep-indexed #(if (even? %1) %2)))))

(def y_max
  (apply max (->> (flatten part1-lines)
                  (keep-indexed #(if (even? %1) %2)))))
(defn gen-points [x up-to-y]
  (->> (range y_min (inc up-to-y))
       (map #(vector x %))))

(defn gen-points-for-line [[[x0 y0] [x1 y1]]]
  (if (= x0 x1)
    (->> (range (min y0 y1) (inc (max y0 y1)))
         (map #(vector x0 %)))
    (->> (range (min x0 x1) (inc (max x0 x1)))
         (map #(vector % y0)))))

(def all-points
  (partition 2 (flatten
                 (->> (range x_min (inc x_max))
                      (map #(gen-points % y_max))))))

(defn horizontal-or-vertical-line-at-point? [[x y] [[x0 y0] [x1 y1]]]
  "Checks if point (x,y) is in the line drawn by (x0,y0) (x1,y1)"
  (if (and (not= x0 x1) (not= y0 y1))
    false
    (cond
      (and (= x x0) (= y y0)) true
      (and (= x x1) (= y y1)) true
      (and (not= x x0 x1) (not= y y0 y1)) false
      :else (if (= x x0 x1)
              (<= (min y0 y1) y (max y0 y1))
              (<= (min x0 x1) x (max x0 x1))))))

; '([0 9] [2 9])  '([0 9] [5 9])
; '([7 0] [7 4])  '([9 4] [3 4])
(defn lines-intersect [[[x0 y0] [x1 y1]] [[x2 y2] [x3 y3]]]
  (if (or (and (not= x0 x1) (not= y0 y1)) (and (not= x2 x3) (not= y2 y3)))
    nil
    (sets/intersection (into #{} (gen-points-for-line [[x0 y0] [x1 y1]]))
                       (into #{} (gen-points-for-line [[x2 y2] [x3 y3]])))))
    ;(cond)))
      ;(and (= x0 x2) (= y0 y2)) true
      ;(and (= x0 x3) (= y0 y3)) true
      ;(and (= x1 x2) (= y1 y2)) true
      ;(and (= x1 x3) (= y1 y3)) true
      ;(seq
      ;  (sets/intersection (into #{} (gen-points-for-line [[x0 y0] [x1 y1]]))
      ;                     (into #{} (gen-points-for-line [[x2 y2] [x3 y3]])))) true
      ;:else false)))


(defn overlapping-lines-at [point lines]
  "counts number of overlapping lines at a specific point"
  (apply +
         (->> lines
              (map #(if (horizontal-or-vertical-line-at-point? point %)
                      1
                      0)))))


;For each coordinate in the system, filter those that has > 1 lines at point
(defn part1 []
  (count (->> all-points
              (map #(overlapping-lines-at % part1-lines))
              (filter #(> % 1)))))

(comment (part1))

;(contains? (into #{} part1-lines) '((0 0) (8 9)))
;(set (mapcat set '(#{[2 2] [2 3]} #{[4 4] [5 5]})))

;(set (concat #{[1 1]} (set (mapcat set '(#{[2 2] [2 3]} #{[4 4] [5 5]})))))

(defn part1 []
  (loop [lines part1-lines
         intersecting-points nil]
    (if (empty? lines)
      (count intersecting-points)
      (let [found-intersections (set (mapcat set
                                             (->> (rest lines)
                                                  (map #(lines-intersect (first lines) %)))))]
        (recur (rest lines) (set (concat intersecting-points found-intersections)))))))



