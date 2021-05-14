(ns learning-clojure.codewars.backspaces-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.codewars.backspaces :refer [clean-string]]))

(deftest clean-string-test
  (is (= "ac" (clean-string "abc#d##c")))
  (is (= "" (clean-string "abc####d##c#")))
  (is (= "jf" (clean-string "abjd####jfk#")))
  (is (= "gdasda" (clean-string "gfh#jds###d#dsd####dasdaskhj###dhkjs####df##s##d##")))
  (is (= "6+yqw8hfklsd-=-f" (clean-string "831####jns###s#cas/*####-5##s##6+yqw87e##hfklsd-=-28##fds##")))
  (is (= "jdsfdasns" (clean-string "######831###dhkj####jd#dsfsdnjkf###d####dasns")))
  (is (= "" (clean-string "")))
  (is (= "" (clean-string "#######")))
  (is (= "sa" (clean-string "####gfdsgf##hhs#dg####fjhsd###dbs########afns#######sdanfl##db#####s#a")))
  (is (= "hskjdgd" (clean-string "#hskjdf#gd")))
  (is (= "hsk48hjjdfgd" (clean-string "hsk48hjjdfgd"))))
