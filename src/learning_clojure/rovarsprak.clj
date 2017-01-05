(ns learning-clojure.rovarsprak)
(require '[clojure.test :as test :refer :all])

(defn consonant? [x]
  (re-matches #"[b-df-hj-np-tv-xz]" (str (clojure.string/lower-case x))))

;; if x is a consonant: return x+"o"+x, else do nothing (return x)
(defn rovarsprak-char [x]
  (apply str (if (consonant? x)
               (str x "o" x)
               (str x))))

(deftest test-rovarsprak-char
  (is (= "hoh" (rovarsprak-char "h")))
  (is (= "a" (rovarsprak-char "a"))))

;; converts given string to rovarsprak
(defn rovarsprak [x]
  (apply str (map rovarsprak-char (map char x))))

(deftest test-rovarsprak
  (is (= "hohejoj" (rovarsprak "hej")))
  (is (= "hohejoj popå dodigog" (rovarsprak "hej på dig"))))


(defn- karpsravor-rec [x]
  (if (nil? (first x)) '()
                       (if (consonant? (first x))
                         (list (first x) (karpsravor-rec (drop 3 x)))
                         (list (first x) (karpsravor-rec (rest x))))))

;; decodes the given rovarsprak x
(defn karpsravor [x]
  (apply str (flatten (karpsravor-rec x))))

(deftest test-karpsravor
  (is (= (karpsravor "hohejoj") "hej"))
  (is (= (karpsravor "hohejoj popå dodigog") "hej på dig"))
  (is (= (karpsravor (rovarsprak "hej")) "hej")))


(run-tests)