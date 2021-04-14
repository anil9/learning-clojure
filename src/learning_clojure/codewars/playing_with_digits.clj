(ns learning-clojure.codewars.playing-with-digits)

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

(defn pow [item exp]
  (long (Math/pow item exp)))

(defn inc-pow [p idx item]
  (pow item (+ p idx)))


(defn digits [n]
  (loop [n n
         res ()]
    (if (< n 10)
      (conj res n)
      (recur (quot n 10) (conj res (rem n 10))))))


(defn dig-pow [n p]
  "want to find an int k, if it exists, such as the sum of digits of n
  taken to the successive powers of p is equal to k * n"
  (let [sum (->> (digits n)
                 (map-indexed (partial inc-pow p))
                 (reduce +))]

    (if (zero? (mod sum n))
      (quot sum n)
      -1)))
; ---------------------------EUREKA
(def eureka-start-exponent 1)

(defn sum-dig-pow [from to]
  (->> (range from (inc to))
       (filter (fn [x] (pos? (dig-pow x eureka-start-exponent))))))
; ---------------------------prime

(defn prime? [x]
  (nil? (->>
          (range 2 (inc (Math/sqrt x)))
          (some #(= 0 (mod x %))))))


; ---------------------------power-sum-dig-term



(defn sum-digits [n]
  (apply + (digits n)))


(defn log-n-div-log-sum-digits [n sum-digits]
  (Math/round (/ (Math/log n) (Math/log sum-digits))))

(defn is-valid? [n]
  (if (= n 81) true
               (let [sum-digits (sum-digits n)
                     exponent (log-n-div-log-sum-digits n sum-digits)]
                 (and
                   (> n 10)
                   (> sum-digits 1)
                   (> exponent 2)
                   (= n (long (Math/pow sum-digits
                                        exponent)))))))



(defn power-sum-dig-term-wrong-way [n]
  (last (->> (range)
             (filter is-valid?)
             (filter some?)
             (take n))))

;-----------------------------------
(defn digits [n]
  (loop [n n
         res ()]
    (if (< n 10)
      (conj res n)
      (recur (quot n 10) (conj res (rem n 10))))))

(defn sum-digits [n]
  (apply + (digits n)))

(defn log-n-div-log-sum-digits [n sum-digits]
  (Math/round (/ (Math/log n) (Math/log sum-digits))))

(defn has-power-exp? [n]
  (let [sum-digits (sum-digits n)]
    (if (< sum-digits 2)
      false
      (loop [n n
             exp 2]
        (let [res (bigdec (.pow (bigdec sum-digits) exp))]
          (cond
            (= res n) true
            (> res n) false
            :else (recur n (inc exp))))))))

(defn is-valid-with-precision? [n]
  (if (< n 80)
    false
    (has-power-exp? n)))


(defn is-valid? [n]
  (if (< n 80)
    false
    (let [sum-digits (sum-digits n)
          exponent (log-n-div-log-sum-digits n sum-digits)]
      (and
        (> sum-digits 1)
        (= n (bigint (Math/pow sum-digits
                               exponent)))))))



(defn lots-pows [need-precision b]
  (cond
    (true? need-precision)
    (->> (range 2 40)
         (map (fn [e] (bigdec (.pow (bigdec b) e))))
         (filter is-valid-with-precision?)
         (filter some?))
    :else
    (->> (range 2 40)
         (map (fn [e] (bigint (Math/pow b e))))
         (filter is-valid?)
         (filter some?))))


(defn power-sum-dig-term [n]
  (let [precision-mode (> n 31)]
    (if (true? precision-mode)
      (bigint (last
                (take n
                      (sort
                        (->> (range 2 150)
                             (mapcat (partial lots-pows precision-mode))
                             (distinct))))))
      (bigint (last
                (take n
                      (sort
                        (->> (range 2 150)
                             (mapcat (partial lots-pows precision-mode))
                             (distinct)))))))))


;(defn power-sum-dig-term [n]
;  (take n
;        (sort
;          (->> (range 2 300)
;               (mapcat (partial lots-pows true))
;               (distinct)))))


(defn find-faulty-num [max]
    (loop [i 2
           prev-res (power-sum-dig-term (dec i))
           curr-res (power-sum-dig-term i)
           numbers [prev-res]]
      (cond
        (= max i) (conj numbers curr-res)
        (<= curr-res prev-res) numbers
        :else (recur (inc i) curr-res (power-sum-dig-term (inc i)) (conj numbers curr-res)))))



;(take (+ n 10000)))))))




; LÖSNING:
(comment (defn digits [n]
           (loop [n n
                  res ()]
             (if (< n 10)
               (conj res n)
               (recur (quot n 10) (conj res (rem n 10))))))

         (defn sum-digits [n]
           (apply + (digits n)))

         (defn log-n-div-log-sum-digits [n sum-digits]
           (Math/round (/ (Math/log n) (Math/log sum-digits))))

         (defn has-power-exp? [n]
           (let [sum-digits (sum-digits n)]
             (if (< sum-digits 2)
               false
               (loop [n n
                      exp 2]
                 (let [res (bigdec (.pow (bigdec sum-digits) exp))]
                   (cond
                     (= res n) true
                     (> res n) false
                     :else (recur n (inc exp))))))))

         (defn is-valid-with-precision? [n]
           (if (< n 80)
             false
             (has-power-exp? n)))


         (defn is-valid? [n]
           (if (< n 80)
             false
             (let [sum-digits (sum-digits n)
                   exponent (log-n-div-log-sum-digits n sum-digits)]
               (and
                 (> sum-digits 1)
                 (= n (bigint (Math/pow sum-digits
                                        exponent)))))))



         (defn lots-pows [need-precision b]
           (cond
             (true? need-precision)
             (->> (range 2 40)
                  (map (fn [e] (bigdec (.pow (bigdec b) e))))
                  (filter is-valid-with-precision?)
                  (filter some?))
             :else
             (->> (range 2 40)
                  (map (fn [e] (bigint (Math/pow b e))))
                  (filter is-valid?)
                  (filter some?))))


         (defn power-sum-dig-term [n]
           (let [precision-mode (> n 31)]
             (if (true? precision-mode)
               (bigint (last
                         (take n
                               (sort
                                 (->> (range 2 150)
                                      (mapcat (partial lots-pows precision-mode))
                                      (distinct))))))
               (bigint (last
                         (take n
                               (sort
                                 (->> (range 2 150)
                                      (mapcat (partial lots-pows precision-mode))
                                      (distinct))))))))))