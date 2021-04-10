(ns learning-clojure.codewars.human-readable-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.human-readable :refer [
                                                              formatDuration
                                                              years
                                                              days
                                                              hours
                                                              minutes
                                                              only-seconds
                                                              num-to-str]]))

(deftest years-test
  (is (= 1 (years (* 3600 24 365))))
  (is (= 0 (years (* 3600 24 364))))
  (is (= 3 (years (* 3600 24 365 3)))))



(deftest days-test
  (is (= 1 (days (* 3600 24))))
  (is (= 1 (days (* 3600 25))))
  (is (= 2 (days (* 3600 48))))
  (is (= 0 (days (* 3600 24 365))))
  (is (= 364 (days (* 3600 24 364)))))

(deftest hours-test
  (is (= 1 (hours 3600)))
  (is (= 0 (hours 3599)))
  (is (= 1 (hours 3620)))
  (is (= 1 (hours 7199)))
  (is (= 2 (hours 7200)))
  (is (= 0 (hours (* 3600 24)))))


(deftest minutes-test
  (is (= 1 (minutes 60)))
  (is (= 0 (minutes 59)))
  (is (= 1 (minutes 119)))
  (is (= 2 (minutes 120)))
  (is (= 1 (minutes 3660))))

(deftest only-seconds-test
  (is (= 10 (only-seconds 10)))
  (is (= 59 (only-seconds 59)))
  (is (= 0 (only-seconds 60)))
  (is (= 0 (only-seconds 0)))
  (is (= 1 (only-seconds 61)))
  (is (= 59 (only-seconds 3659))))

(deftest num-to-str-test
  (is (= "1 second" (num-to-str 1 "second" "seconds")))
  (is (= "2 seconds" (num-to-str 2 "second" "seconds")))
  (is (= "1 minute" (num-to-str 1 "minute" "minutes")))
  (is (= "2 minutes" (num-to-str 2 "minute" "minutes")))
  (is (= "" (num-to-str 0 "minute" "minutes"))))


(deftest formatDuration-test
  (is (= "now" (formatDuration 0)))
  (is (= "1 second" (formatDuration 1)))
  (is (= "1 minute and 2 seconds" (formatDuration 62)))
  (is (= "2 minutes" (formatDuration 120)))
  (is (= "1 hour" (formatDuration 3600)))
  (is (= "1 hour, 1 minute and 2 seconds" (formatDuration 3662)))
  (is (= "1 year, 19 days, 18 hours, 19 minutes and 46 seconds" (formatDuration 33243586))))





