(ns learning-clojure.advent-of-code.2021.input-reader
  (:require [clojure.string :as str]))

(def standard-path "/home/andreas/temp/advent-of-code/")

(defn raw-input [file-path]
  (slurp (str standard-path file-path)))

(defn get-line-separated-input [file-path]
  (str/split-lines (raw-input file-path)))

(defn get-int-input [file-path]
  (->> (get-line-separated-input file-path)
       (map #(Integer/parseInt %))))

(defn split-line-input-at [file-path regex]
  (->> (get-line-separated-input file-path)
       (map #(str/split % (re-pattern regex)))))

(defn split-input-at [file-path regex]
  (str/split (raw-input file-path) (re-pattern regex)))
