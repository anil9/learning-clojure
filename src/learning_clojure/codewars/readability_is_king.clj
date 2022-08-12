(ns learning-clojure.codewars.readability-is-king
  (:require [clojure.string :as s]))
;; https://www.codewars.com/kata/52b2cf1386b31630870005d4/train/clojure
(def word-per-sentence-factor 0.39)
(def syllables-per-word-factor 11.8)
(def ignore-chars #"[-—'’()…]")
(def vowels #{\a \e \i \o \u})

(defn words-per-sentence [sentence]
  (s/split sentence #"\s+"))

(defn is-vowel? [letter] (contains? vowels letter))

(defn count-syllables [word]
  (loop [current word
         prev-letter-vowel? false
         syllables 0]
    (if (empty? current)
      syllables
      (let [char-is-vowel (is-vowel? (first current))]
        (if (not char-is-vowel)
          (recur (rest current) false syllables)
          (if prev-letter-vowel?
            (recur (rest current) true syllables)
            (recur (rest current) true (inc syllables))))))))

(defn text->sentences [text]
  (map s/trim (s/split text #"[\.!?]")))

(defn flesch-kincaid [text]
  (let [cleaned (-> text
                    (s/lower-case)
                    (s/replace ignore-chars "")
                    (s/trim))
        sentences (text->sentences cleaned)
        avg-words-per-sentence (/ (->> sentences
                                       (map words-per-sentence)
                                       (map count)
                                       (reduce +))
                                  (count sentences))
        words (->> sentences
                   (map words-per-sentence)
                   (flatten))
        avg-syllable-per-word (/ (->> words
                                      (map count-syllables)
                                      (reduce +))
                                 (count words))]
    (with-precision 2 (+
                       (* word-per-sentence-factor avg-words-per-sentence)
                       (* syllables-per-word-factor avg-syllable-per-word)
                       -15.59))))

(comment
  (flesch-kincaid "The turtle is leaving")
  (flesch-kincaid "To be or not to be. That is the question.")
  (count-syllables "hi")
  (count-syllables "question"))
