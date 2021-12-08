(ns learning-clojure.advent-of-code.2021.input-reader
  (:require [clojure.string :as str]))

(def standard-path "/home/andreas/temp/advent-of-code/")

(defn get-line-separated-input [x]
  (str/split-lines (slurp (str standard-path x))))

(defn get-int-input [x]
  (->> (get-line-separated-input x)
       (map #(Integer/parseInt %))))

