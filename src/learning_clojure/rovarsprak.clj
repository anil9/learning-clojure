(ns learning-clojure.rovarsprak (:use clojure.test))

(defn consonant? [x]
  (re-matches #"[b-df-hj-np-tv-xz]" (str (clojure.string/lower-case x))))


(defn rovarsprak-char [x]
  (apply str (if (consonant?  x)
               (str x "o" x)
               (str x))))

(deftest test-rovarsprak-char
  (is (="hoh" (rovarsprak-char "h")))
  (is (="a" (rovarsprak-char "a"))))

(defn rovarsprak [x]
  (apply str (map rovarsprak-char (map char x))))


(deftest test-rovarsprak
  (is (="hohejoj" (rovarsprak "hej")))
  (is (="hohejoj popå dodigog" (rovarsprak "hej på dig"))))



(defn karpsravor-rec [x]
  (if (nil? (first x)) '()
                       (if (consonant? (first x))
                         (list (first x) (karpsravor-rec (drop 3 x)))
                         (list (first x) (karpsravor-rec (rest x))))))

(defn karpsravor [x]
  (apply str(flatten (karpsravor-rec x))))

(deftest test-karpsravor
  (is (= (karpsravor "hohejoj") "hej"))
  (is (= (karpsravor "hohejoj popå dodigog") "hej på dig"))
  (is (= (karpsravor (rovarsprak "hej")) "hej")))


(run-tests)