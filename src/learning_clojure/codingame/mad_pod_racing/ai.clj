(ns learning-clojure.codingame.mad-pod-racing.ai
  (:require [learning-clojure.codingame.mad-pod-racing.pod-simulation :as simulation]
            [clojure.set :as set]
            [clojure.tools.trace :as tools]))

(defn unique-random-thrust [n]
  (let [a-set (set (take n (repeatedly #(rand-int 100))))]
    (mapv int (concat a-set (set/difference (set (take n (range))) a-set)))))

(defn play-it [board thrust num-ticks]
  (loop [b board
         n num-ticks
         thrust-chain [thrust]]
    (if (or
         (= 0 n)
         (simulation/reached-target? b)
         (= 0 (get-in b [:pod :remaining-turns])))
      {:thrust thrust-chain
       :distance-to-target (simulation/distance-to-target b)}
      (let [next-thrust (int (rand-int 101))]
        (recur 
          (simulation/tick b (last thrust-chain))
          (dec n)
          (conj thrust-chain next-thrust))))))

(defn full-speed [board num-ticks]
  (loop [b board
         n num-ticks
         thrust-chain [100]]
    (if (or
          (= 0 n)
          (simulation/reached-target? b)
          (= 0 (get-in b [:pod :remaining-turns])))
      {:thrust thrust-chain
       :distance-to-target (simulation/distance-to-target b)}
      (let [next-thrust 100]
        (recur 
          (simulation/tick b (last thrust-chain))
          (dec n)
          (conj thrust-chain next-thrust))))))

(defn highest-scoring-input [board num-ticks num-tries]
  (let [thrust (conj (unique-random-thrust num-tries) 100)
        results (conj (->> thrust
                           (map #(play-it board % num-ticks))) (full-speed board num-ticks))
        sorted-by-ticks (sort (map #(count (:thrust %)) results))
        sorted-by-distance (sort (map #(:distance-to-target %) results))]
    (if (= 1 (get (frequencies sorted-by-ticks) (first sorted-by-ticks)))
      ; one has fewer ticks then the rest. We pick that one.
      (first (filter #(= (first sorted-by-ticks) (count (:thrust %))) results)) 
      ; otherwise select with smallest distance
      (first (filter #(= (first sorted-by-distance) (:distance-to-target %)) results)))))


(comment
  (unique-random-thrust 8)
  (time (highest-scoring-input simulation/initial-game 80 80))
  (highest-scoring-input (simulation/initiate-board [1571 6214] [4964 5339]) 80 80)
  (conj [3] 3))
