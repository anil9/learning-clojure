(ns learning-clojure.encrypt-this-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.encrypt-this :refer [encrypt-this]])
  (:require [learning-clojure.encrypt-this :refer [replace-first-char-with-ascii]])
  (:require [learning-clojure.encrypt-this :refer [second-letter-switched-with-last]])

  )

(deftest encrypt-this-test
  (is (= "72olle" (encrypt-this "Hello")))
  (is (= "103doo" (encrypt-this "good")))
  (is (= "104olle 119drlo" (encrypt-this "hello world")))
  (is (= "119e" (encrypt-this "we")))
  )

(deftest replace-first-char-with-ascii-test
  (is (= "72ello" (replace-first-char-with-ascii "Hello")))
  (is (= "103ood" (replace-first-char-with-ascii "good")))
  )

(deftest second-letter-switched-with-last-test
  (is (= "Holle" (second-letter-switched-with-last "Hello")))
  (is (= "tEsT" (second-letter-switched-with-last "tTsE")))
  (is (= "we" (second-letter-switched-with-last "we")))
  )
