(ns learning-clojure.codewars.buddy-pairs)

(defn proper-divisors [n]
  (->> (range 1 (inc (Math/sqrt n)))
       (filter #(= 0 (mod n %)))
       (mapcat #(if (or (= % (quot n %)) (= n (quot n %)))
                  (list %)
                  (list % (quot n %))))))

(defn get-buddy [n]
  (let [m (dec (apply + (proper-divisors n)))]
    (cond
      (<= m n) nil
      (= (inc n) (apply + (proper-divisors m))) (list n m)
      :else nil)))

(defn buddy [start nd]
  (let [res (->> (range start (inc nd))
                 (map get-buddy)
                 (filter some?)
                 (first))]
    (if res (str res)
            "Nothing")))


;Below code is for looking at the data only
(defn buddy-list [start nd]
  (->> (range start (inc nd))
       (map get-buddy)
       (filter some?)))

;(defn into-map [[x y]] (assoc {} x (proper-divisors x) y (proper-divisors y)))
(defn into-map [x]
  (assoc {} x (proper-divisors x) (dec (apply + (proper-divisors x)))
            (proper-divisors (dec (apply + (proper-divisors x))))))

(spit "numbers.txt" (list (->> (range 10 1000)
                               (map into-map))))

