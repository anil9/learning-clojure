(ns learning-clojure.codewars.matching-subst
  (:require [clojure.string :as str]))

(def date-replacement "2019-01-01")
(def author-replacement "g964")
(def phone-replacement "+1-503-555-0090")
(def version-constant "2.0")
(def replacements {"Date"   date-replacement
                   "Author" author-replacement
                   "Phone"  phone-replacement})
(def keep-keys '("Program" "Author" "Phone" "Date" "Version"))

(defn valid-version? [s]
  (some? (re-matches #"(\d+\.\d+)" s)))

(defn valid-phone? [s]
  (some? (re-matches #"\+1-\d{3}-\d{3}-\d{4}" s)))

(defn str-to-map [s]
  (into {} (map #(str/split % #": ") (str/split-lines s))))


(defn replace-version [m r]
  (update m "Version" #(if (= version-constant %)
                         %
                         r)))

(defn format-key-val [m k]
  (str k ": " (get m k)))

(defn formatted-str [output-map]
  (str/join " " (->> keep-keys
                     (map #(format-key-val output-map %)))))

(defn change [s prog version]
  (let [properties-map (conj (str-to-map s) {"Program" prog})
        invalid-version (not (valid-version? (get properties-map "Version")))
        invalid-phone (not (valid-phone? (get properties-map "Phone")))]
    (if (or invalid-phone invalid-version)
      "ERROR: VERSION or PHONE"
      (let [map-with-replaced-version (replace-version properties-map version)
            output-map (merge map-with-replaced-version replacements)]
        (formatted-str output-map)))))