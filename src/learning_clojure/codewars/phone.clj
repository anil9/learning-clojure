(ns learning-clojure.codewars.phone)
;; https://www.codewars.com/kata/525f50e3b73515a6db000b83

(defn create-phone-number [nums]
  (let [first-part (take 3 nums)
        second-part (take 3 (drop 3 nums))
        last-part (drop 6 nums)]
    (str "(" (apply str first-part) ")" " " (apply str second-part) "-" (apply str last-part))))
