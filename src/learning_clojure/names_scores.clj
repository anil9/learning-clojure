(ns learning-clojure.names-scores)
(require '[clojure.test :as test :refer :all])

;; splits string x
(defn split-fn [x]
  (filter #(re-matches #"\w+" %) (clojure.string/split x #"\"")))

(deftest split-correctly
  (is (= '("MARY" "PATRICIA") (split-fn "\"MARY\",\"PATRICIA\"")))
  (is (= '("MARY") (split-fn "\"MARY\""))))

;; reads file into list
(defn read-into-list [file-name]
  (split-fn (slurp file-name)))

;; calculates the alphabetical score of the name given as param
(defn alphabetical-score [name]
  (reduce + (map #(- (int %) 64) (seq (clojure.string/upper-case name)))))

(deftest test-alphabetical-score
  (is (= 53 (alphabetical-score "COLIN"))))

(def file-name "/tmp/names.txt")
;; calculates the sum of all (alphabetical-score * index+1)
(defn total-sum []
  (let [alpha-scored-list (map alphabetical-score (sort (read-into-list file-name)))]
    (def result (reduce +' (map-indexed #(* (inc %1) %2) alpha-scored-list)))
    result))

;; prints result of euler #22
(println (total-sum))
(run-tests)