(ns learning-clojure.codewars.adjacent-element-product)

(defn adjacent-element-product [numbers]
  (let [products (->>
                   (map vector numbers (rest numbers))
                   (map (fn [[key val]] (* key val))))]
    (last (sort products))))

(defn adjacent-element-product [numbers]
  (let [products (->>
                   (map vector numbers (rest numbers))
                   (map (fn [[key val]] (* key val))))]
    (last (sort products))))