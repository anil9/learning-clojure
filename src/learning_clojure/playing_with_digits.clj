(ns learning-clojure.playing-with-digits)

(defn digits [n]
  (loop [n n
         res ()]
    (if (< n 10)
      (conj res n)
      (recur (quot n 10) (conj res (rem n 10))))))

(defn pow [item exp]
  (long (Math/pow item exp)))

(defn inc-pow [p idx item]
  (pow item (+ p idx)))

(defn dig-pow [n p]
  "want to find an int k, if it exists, such as the sum of digits of n
  taken to the successive powers of p is equal to k * n"
  (let [sum (->> (digits n)
                 (map-indexed (partial inc-pow p))
                 (reduce +)
                 )]
    (if (zero? (mod sum n))
      (quot sum n)
      -1)))

(def eureka-start-exponent 1)

(defn sum-dig-pow [from to]
  (->> (range from (inc to))
       (filter (fn [x] (pos? (dig-pow x eureka-start-exponent))))))