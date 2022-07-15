(ns learning-clojure.advent-of-code.2021.day9
  (:require
    [learning-clojure.advent-of-code.2021.input-reader :as input-reader]
    [clojure.string :as str]
    [clojure.set :as sets]))
(def day "day9")
(def part1-example-file (str day "/example-input-part1"))
(def part1-file (str day "/input-part1"))
(def part2-example-file (str day "/example-input-part2"))
(def part2-file (str day "/input-part2"))

(defn to-int [chr]
  (let [x (char chr)]
    (Character/digit x 10)))


(def part1-data
  (->> (input-reader/get-line-separated-input part1-example-file)
       (map #(seq (char-array %)))
       (mapv (fn [arr] (->> arr
                            (mapv #(to-int %)))))))
(comment
  (map prn part1-data))

(defn corner? [[x y] corners]
  (contains? corners [x y]))

(defn diag? [[x0 y0] [x1 y1]]
  (not
    (or (and (= x0 x1) (not= y0 y1))
        (and (not= x0 x1) (= y0 y1)))))

(defn generate-possible-points [[x y] matrix]
  (let [max_x (dec (count (first matrix)))
        max_y (dec (count matrix))
        x_low (if (>= (dec x) 0) (dec x) nil)
        x_high (if (<= (inc x) max_x) (inc x) nil)
        y_low (if (>= (dec y) 0) (dec y) nil)
        y_high (if (<= (inc y) max_y) (inc y) nil)
        possible_x (set (remove nil? [x_low x_high x]))
        possible_y (set (remove nil? [y_low y_high y]))
        corners #{[0 0] [0 max_y] [max_x 0] [max_x max_y]}]
    (partition 2 (flatten (->> possible_x
                               (map (fn [coord_x] (->> possible_y
                                                       (filter (fn [coord_y] (not (and (= coord_x x) (= coord_y y)))))

                                                       (filter (fn [coord_y] (if (diag? [coord_x coord_y] [x y])
                                                                               (corner? [coord_x coord_y] corners)
                                                                               true)))
                                                       (map (fn [coord_y] (vector coord_x coord_y)))))))))))


(comment
  (generate-possible-points [2 3] part1-data))

(defn has-lowest-neighbor? [[x y] matrix found-lowest]
  (let [
        possible-points (generate-possible-points [x y] matrix)
        lowest-neighbor (->> possible-points
                             (map (fn [point] (let [coord_x (first point)
                                                    coord_y (second point)]
                                                (get found-lowest [coord_x coord_y])))))
        has-lowest-neighbor (not (empty? (remove nil?
                                                 (flatten lowest-neighbor))))]
    ; (prn [x y] " lowest neighbor(s): " lowest-neighbor)
    ; (prn [x y] " has lowest neighbor? " has-lowest-neighbor)
    has-lowest-neighbor))


(defn lowest-amongst-neighbors? [[x y] matrix]
  (let [elm (get (get matrix y) x)
        possible-points (generate-possible-points [x y] matrix)]
    (empty? (->> possible-points
                 (map #(get (get matrix (second %)) (first %)))
                 (filter #(< % elm))))))



(defn find-lowest [matrix]
  (loop [curr 0
         found-lowest {}]
    ;(do (prn "map current state: " found-lowest)
    (if (= curr 10)
      found-lowest
      (let [newly-found-lowest (apply merge (remove nil?
                                                    (flatten (->> matrix
                                                                  (map-indexed (fn [y y_elm]
                                                                                 (->> y_elm
                                                                                      (map-indexed (fn [x x_elm]
                                                                                                     (if (and
                                                                                                           (= x_elm curr)
                                                                                                           (not (has-lowest-neighbor? [x y] matrix found-lowest))
                                                                                                           (lowest-amongst-neighbors? [x y] matrix))
                                                                                                       ;(do (prn "making hash-map " [x y] x_elm)
                                                                                                       (hash-map [x y] x_elm)
                                                                                                       nil))))))))))]

        ;(prn newly-found-lowest)
        (recur (inc curr) (merge found-lowest newly-found-lowest))))))



;part1-data
(defn part1 []
  (apply + (->> (vals (find-lowest part1-data))
                (map inc))))
(comment
  (find-lowest part1-data)
  (part1))