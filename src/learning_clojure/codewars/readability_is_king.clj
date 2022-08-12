(ns learning-clojure.codewars.readability-is-king
  (:require [clojure.string :as s]))
;; https://www.codewars.com/kata/52b2cf1386b31630870005d4/train/clojure
(def ignore-chars #"[-—'’()…]")

(defn text->words [text]
  (s/split text #"\s+"))

(defn count-syllables [word]
  (count (re-seq #"[aeiou]+" word)))

(defn text->sentences [text]
  (map s/trim (s/split text #"[\.!?]")))

(defn flesch-kincaid [text]
  (let [cleaned (-> text
                    (s/lower-case)
                    (s/replace ignore-chars "")
                    (s/trim))
        sentences (text->sentences cleaned)
        words (text->words cleaned)
        avg-words-per-sentence (/ (count words)
                                  (count sentences))
        num-syllables (->> words
                           (map count-syllables)
                           (reduce +))
        avg-syllable-per-word (/ num-syllables
                                 (count words))]
    (with-precision 2 (+
                       (* 0.39 avg-words-per-sentence)
                       (* 11.8 avg-syllable-per-word)
                       -15.59))))

(comment
  (flesch-kincaid "The turtle is leaving")
  (count (re-seq #"[.!?]" "The turtle is leaving."))
  (count (re-seq #"\W+" "The turtle is leaving."))
  (s/split "To be or not to be. That is the question." #"[.!?] *")
  (flesch-kincaid "To be or not to be. That is the question.")
  (count-syllables "hi")
  (count-syllables "question"))
