(ns learning-clojure.codewars.matching-subst-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.matching-subst :refer [change
                                                              valid-version?
                                                              valid-phone?]]))

(defn do-test [s prog version expect]
  (let [actual (change s prog version)]
    (is (= actual expect))))

(deftest change-test
  (def s1 "Program title: Primes\nAuthor: Kern\nCorporation: Gold\nPhone: +1-503-555-0091\nDate: Tues April 9, 2005\nVersion: 6.7\nLevel: Alpha")
  (do-test s1 "Ladder" "1.1" "Program: Ladder Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 1.1")
  (def s2 "Program title: Balance\nAuthor: Dorries\nCorporation: Funny\nPhone: +1-503-555-0095\nDate: Tues July 19, 2014\nVersion: 6.7\nLevel: Release")
  (do-test s2 "Circular" "1.5" "Program: Circular Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 1.5")
  (def s13 "Program title: Primes\nAuthor: Kern\nCorporation: Gold\nPhone: +1-503-555-0090\nDate: Tues April 9, 2005\nVersion: 67\nLevel: Alpha")
  (do-test s13 "Ladder" "1.1" "ERROR: VERSION or PHONE")
  (def s15 "Program title: Primes\nAuthor: Kern\nCorporation: Gold\nPhone: -1-503-555-0091\nDate: Tues April 9, 2005\nVersion: 6.7.5\nLevel: Alpha")
  (do-test s15 "Ladder" "1.1" "ERROR: VERSION or PHONE"))

(deftest valid-versions-test
  (is (true? (valid-version? "1.1")))
  (is (true? (valid-version? "1.12323")))
  (is (true? (valid-version? "11212.1"))))


(deftest invalid-versions-test
  (is (false? (valid-version? "1")))
  (is (false? (valid-version? "1.")))
  (is (false? (valid-version? "1.1.")))
  (is (false? (valid-version? "1.1.2")))
  (is (false? (valid-version? "1.1.2.22222.10"))))


(deftest valid-phone?-test
  (is (true? (valid-phone? "+1-503-555-0090")))
  (is (true? (valid-phone? "+1-504-555-0090")))
  (is (true? (valid-phone? "+1-503-555-1111")))
  (is (true? (valid-phone? "+1-503-555-0090"))))

(deftest invalid-phone?-test
  (is (false? (valid-phone? "1")))
  (is (false? (valid-phone? "")))
  (is (false? (valid-phone? "1.")))
  (is (false? (valid-phone? "1.1.")))
  (is (false? (valid-phone? "1.1.")))
  (is (false? (valid-phone? "1-503-555-1111")))
  (is (false? (valid-phone? "++1-503-555-1111")))
  (is (false? (valid-phone? "+1--503-555-1111")))
  (is (false? (valid-phone? "-1-503-555-0091")))
  (is (false? (valid-phone? "+1+503-555-1111")))
  (is (false? (valid-phone? "+1*503-555-1111")))
  (is (false? (valid-phone? "+2-503-555-1111")))
  (is (false? (valid-phone? "+2-503-5551-1111")))
  (is (false? (valid-phone? "+2-5031-555-1111")))
  (is (false? (valid-phone? "+2-503-555-11111"))))

