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
(def part2-lines (->> (input-reader/split-line-input-at part2-file #" -> ")
                      (map #(str-line-to-int %))))

(defn get-m [y k x]
  (- y (* k x)))


(defn get-m-for-k [[[x0 y0] [x1 y1]] k]
  (let [m0 (get-m y0 k x0)
        m1 (get-m y1 k x1)]
    (if (= m0 m1) m0)))


(defn determine-k-m [line]
  "calculates k and m of line by solving equation y=kx+m, where k=-1 or k=1"
  (let [pos_k_m (get-m-for-k line 1)
        neg_k_m (get-m-for-k line -1)]
    (if pos_k_m
      {:k 1 :m pos_k_m}
      {:k -1 :m neg_k_m})))


(defn gen-points-for-line [[[x0 y0] [x1 y1]]]
  "generates all points for input line"
  (if (or (and (not= x0 x1) (not= y0 y1)))
    ;diagonal line
    (let [{k :k m :m} (determine-k-m [[x0 y0] [x1 y1]])
          min_x (min x0 x1)
          max_x (max x0 x1)]
      (->> (range  min_x (inc max_x))
           (map #(vector % (+ (* k %) m)))))
    ;not diagonal
    (if (= x0 x1)
      (->> (range (min y0 y1) (inc (max y0 y1)))
           (map #(vector x0 %)))
      (->> (range (min x0 x1) (inc (max x0 x1)))
           (map #(vector % y0))))))

(defn lines-intersect [[[x0 y0] [x1 y1]] [[x2 y2] [x3 y3]]]
  "get all points where the input lines intersect"
  (if (or (and (not= x0 x1) (not= y0 y1)) (and (not= x2 x3) (not= y2 y3)))
    nil ; only horizontal or vertical lines allowed
    (sets/intersection (into #{} (gen-points-for-line [[x0 y0] [x1 y1]]))
                       (into #{} (gen-points-for-line [[x2 y2] [x3 y3]])))))


(defn part1 []
  (loop [lines part1-lines
         intersecting-points nil]
    (if (empty? lines)
      (count intersecting-points)
      (let [found-intersections (set (mapcat set
                                             (->> (rest lines)
                                                  (map #(lines-intersect (first lines) %)))))]
        (recur (rest lines) (set (concat intersecting-points found-intersections)))))))


(defn lines-intersect-with-diag [[[x0 y0] [x1 y1]] [[x2 y2] [x3 y3]]]
    (sets/intersection (into #{} (gen-points-for-line [[x0 y0] [x1 y1]]))
                       (into #{} (gen-points-for-line [[x2 y2] [x3 y3]]))))

(defn part2 []
  (loop [lines part2-lines
         intersecting-points nil]
    (if (empty? lines)
      (count intersecting-points)
      (let [found-intersections (set (mapcat set
                                             (->> (rest lines)
                                                  (map #(lines-intersect-with-diag (first lines) %)))))]
        (recur (rest lines) (set (concat intersecting-points found-intersections)))))))

(comment
  (part1)
  (part2))