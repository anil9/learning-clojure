(ns learning-clojure.codewars.human-readable)

(def minutes-in-hour 60)
(def hours-in-day 24)
(def days-in-year 365)
(def seconds-in-minute 60)
(def seconds-in-hour (* seconds-in-minute 60))
(def seconds-in-day (* seconds-in-hour hours-in-day))
(def seconds-in-year (* seconds-in-day days-in-year))

(defn years [secs]
  (quot secs seconds-in-year))

(defn days [secs]
  (mod (quot secs seconds-in-day) days-in-year))


(defn hours [secs]
  (mod (quot secs seconds-in-hour) hours-in-day))

(defn minutes [secs]
  (mod (quot secs seconds-in-minute) minutes-in-hour))

(defn only-seconds [secs]
  (mod secs seconds-in-minute))

(defn num-to-str [num singular plural]
  (cond
    (zero? num) ""
    (= 1 num) (str num " " singular)
    :else (str num " " plural)))

(defn format-text [strings]
  (let [no-empty-strings (filter not-empty strings)]
    (cond
      (= 1 (count no-empty-strings)) (apply str no-empty-strings)
      (= 2 (count no-empty-strings)) (clojure.string/join " and " no-empty-strings)
      :else (apply str (clojure.string/join ", " (butlast no-empty-strings)) " and " (last no-empty-strings)))))


(defn formatDuration [secs]
  (if (= 0 secs)
    "now"
    (let [years (num-to-str (years secs) "year" "years")
          days (num-to-str (days secs) "day" "days")
          hours (num-to-str (hours secs) "hour" "hours")
          minutes (num-to-str (minutes secs) "minute" "minutes")
          seconds (num-to-str (only-seconds secs) "second" "seconds")]
      (format-text [years days hours minutes seconds]))))