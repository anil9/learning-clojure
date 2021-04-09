(ns learning-clojure.codewars.encrypt-this
  (:require [clojure.string :as str]))

(defn replace-first-char-with-ascii [x]
  (apply str
         (concat
           (str (int (first x)))
           (rest x))))

(defn second-letter-switched-with-last [x]
  (if (< (count x) 3)
    x
    (apply str
           (concat
             (str (first x))
             (str (last x))
             (drop 2 (drop-last x))
             (str (second x))))))

(defn encrypt-this [text]
  "first letter in each word is converted to ascii code
  the second letter is switched with the last letter"
   (->> (str/split text #" ")
        (map second-letter-switched-with-last)
        (map replace-first-char-with-ascii)
        (str/join " ")))