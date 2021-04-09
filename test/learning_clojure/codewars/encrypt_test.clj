(ns learning-clojure.codewars.encrypt-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.encrypt :refer :all]))

(deftest tests-seconds
  (testing "seconds tests"
    (is (= "" (seconds "")))
    (is (= "b" (seconds "ab")))
    (is (= "2468" (seconds (range 1 10))))
    ))

(deftest tests-non-seconds
  (testing "non-seconds tests"
    (is (= "" (non-seconds "")))
    (is (= "a" (non-seconds "ab")))
    (is (= "13579" (non-seconds (range 1 10))))
    ))

(deftest sample-tests-encrypt
  (testing "Encryption tests"
    (is (= "" (encrypt "" 5)))
    (is (= nil (encrypt nil 2)))
    (is (= "This is a test!" (encrypt "This is a test!" 0)))
    (is (= "hsi  etTi sats!" (encrypt "This is a test!" 1)))
    (is (= "s eT ashi tist!" (encrypt "This is a test!" 2)))
    (is (= " Tah itse sits!" (encrypt "This is a test!" 3)))
    (is (= "This is a test!" (encrypt "This is a test!" 4)))
    (is (= "This is a test!" (encrypt "This is a test!" -1)))
    (is (= "hskt svr neetn!Ti aai eyitrsig"
           (encrypt "This kata is very interesting!" 1)))))

(deftest tests-two-part-str
  (testing "Dividing a string in two parts"
    (is (= '("This is" " a test") (two-part-str "This is a test")))
    (is (= '("This is" " a test!") (two-part-str "This is a test!")))
    ))

(deftest sample-tests-decrypt
  (testing "Decryption tests"
    (is (= "This is a test!" (decrypt "This is a test!" 0)))
    (is (= "This is a test" (decrypt "hsi  etTi sats" 1)))
    (is (= "This is a test!" (decrypt "hsi  etTi sats!" 1)))


    (is (= "This is a test!" (decrypt "s eT ashi tist!" 2)))
    (is (= "This is a test!" (decrypt " Tah itse sits!" 3)))
    (is (= "This is a test!" (decrypt "This is a test!" 4)))
    (is (= "This is a test!" (decrypt "This is a test!" -1)))
    (is (= "This kata is very interesting!"
           (decrypt "hskt svr neetn!Ti aai eyitrsig" 1)))))