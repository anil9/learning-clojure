(ns learning-clojure.codewars.gap-in-primes)
;https://www.codewars.com/kata/561e9c843a2ef5a40c0000a4/train/clojure

(defn prime? [n]
  (not (->> (range 2 (inc (int (Math/sqrt n))))
            (some #(= 0 (mod n %))))))

(defn within-range? [n range]
  (<= n range))

(defn gap [g m n]
  (let [primes (vec (->> (range m (inc n))
                         (filter prime?)))
        lookup-prime (set primes)
        primes-that-also-is-prime-after-added-g (set (->> primes
                                                          (filter #(contains? lookup-prime (+ % g)))))
        prime-with-gap (->> primes
                            (filter #(within-range? (+ % g) n))
                            (keep-indexed #(if (and
                                                 (contains? primes-that-also-is-prime-after-added-g %2)
                                                 (or (= (last primes) %2) (<= (+ %2 g) (nth primes (inc %1)))))
                                             %2))
                            (first))]
    (when (some? prime-with-gap) [prime-with-gap (+ prime-with-gap g)])))

;(time (gap 333 1000000 2000000))
; => "Elapsed time: 4023.882939 msecs"
