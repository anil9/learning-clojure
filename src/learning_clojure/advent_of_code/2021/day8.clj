(ns learning-clojure.advent-of-code.2021.day8
  (:require
    [learning-clojure.advent-of-code.2021.input-reader :as input-reader]
    [clojure.string :as str]
    [clojure.set :as sets]))
(def day "day8")
(def part1-example-file (str day "/example-input-part1"))
(def part1-file (str day "/input-part1"))
(def part2-example-file (str day "/example-input-part2"))
(def part2-file (str day "/input-part2"))

(defn input-at-line [line]
  (let [line-input (->> (str/split line #"\|")
                        (map #(str/trim %)))]
    {:signal-patterns (set (str/split (first line-input) #"\s+"))
     :output-value    (str/split (second line-input) #"\s+")}))
(def part1-data
  (->> (input-reader/get-line-separated-input part1-file)
       (map #(input-at-line %))))

(def part2-data
  (->> (input-reader/get-line-separated-input part2-example-file)
       (map #(input-at-line %))))

(def easy-numbers-length {:1 2
                          :4 4
                          :7 3
                          :8 7})

(defn count-easy-numbers [m]
  (let [output-value (:output-value m)]
    (count
      (->> output-value
           (filter #(contains? (set (vals easy-numbers-length)) (count %)))))))

(defn part1 []
  (apply + (->> part1-data
                (map #(count-easy-numbers %)))))


; part 2
(defn get-code [len codes]
  (first (filter #(= (count %) len) codes)))


(defn to-chars [str]
  (if (coll? str)
    (map #(to-chars %) str)
    (seq (char-array str))))

(defn disj-all [coll key & ks]
  (let [args (set (remove nil? (flatten (conj key ks))))
        res (apply disj coll args)]
    res))

(def numbers-length
  (merge easy-numbers-length {:0 6
                              :2 5
                              :3 5
                              :5 5
                              :6 6
                              :9 6}))

(defn deduce-nine [easy-numbers signal-patterns]
  (let [roof-of-seven (sets/difference (set (:7 easy-numbers)) (set (:1 easy-numbers)))]
    (first (->> signal-patterns
                (filter #(= (count %) (:6 numbers-length)))
                (filter (fn [code] (every? #(contains? (set code) %) (sets/union (set (:4 easy-numbers)) roof-of-seven))))))))

(defn deduce-five [easy-numbers nine signal-patterns]
  (first (->> signal-patterns
              (filter #(= (count %) (:5 numbers-length)))
              (filter (fn [code] (or
                                   (every? #(contains? (set code) %) (sets/difference (set nine) (hash-set (first (:1 easy-numbers)))))
                                   (every? #(contains? (set code) %) (sets/difference (set nine) (hash-set (second (:1 easy-numbers)))))))))))


(defn deduce-three [easy-numbers nine signal-patterns]
  (first (->> signal-patterns
              (filter #(= (count %) (:3 numbers-length)))
              (filter (fn [code] (every? #(contains? (set code) %) (sets/difference (set nine) (sets/difference (set nine) (hash-set (:4 easy-numbers))))))))))


(defn deduce-two [signal-patterns]
  (first (->> signal-patterns
              (filter #(= (count %) (:2 numbers-length))))))


(defn deduce-six [easy-numbers diff-nine-five signal-patterns]
  (first (->> signal-patterns
              (filter #(= (count %) (:6 numbers-length)))
              (filter (fn [code] (every? #(contains? (set code) %) (sets/difference (set (:8 easy-numbers)) diff-nine-five)))))))


(defn get-number [coded all-numbers-decoded]
  (prn "coded" coded)
  (prn "all-numbers-decoded" all-numbers-decoded)
  (let [found-number (first (->> all-numbers-decoded
                                 (filter #(= (set (to-chars coded)) (second %)))))]
    (prn "found-number" found-number)
    (name (first found-number))))

(defn map-function-on-map-vals [m f]
  (apply merge
         (map (fn [[k v]] {k (f v)})
              m)))

(defn decode-numbers [m]
  (let [easy-numbers (into {} (->> easy-numbers-length
                                   (map #(hash-map (first %) (get-code (second %) (:signal-patterns m))))))
        nine (deduce-nine easy-numbers (disj-all (:signal-patterns m) (vals easy-numbers)))
        five (deduce-five easy-numbers nine (disj-all (:signal-patterns m) (vals easy-numbers) nine))
        three (deduce-three easy-numbers nine (disj-all (:signal-patterns m) (vals easy-numbers) nine five))
        two (deduce-two (disj-all (:signal-patterns m) (vals easy-numbers) nine five three))
        six (deduce-six easy-numbers (sets/difference (set nine) (set five)) (disj-all (:signal-patterns m) (vals easy-numbers) nine five three two))
        zero (first (disj-all (:signal-patterns m) (vals easy-numbers) nine five three two six))
        all-numbers-decoded (map-function-on-map-vals (merge easy-numbers {:9 nine
                                                                           :5 five
                                                                           :3 three
                                                                           :2 two
                                                                           :6 six
                                                                           :0 zero}) #(set (to-chars %)))
        output-value (:output-value m)]

    (Integer/parseInt (apply str (flatten (map #(get-number % all-numbers-decoded) output-value))))))


(defn part2 []
  (->> part2-data
       (map #(decode-numbers %))))


(comment
  (part1)
  (part2))