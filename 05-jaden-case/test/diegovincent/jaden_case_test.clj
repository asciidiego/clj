(ns diegovincent.jaden-case-test
  (:require [clojure.test :refer :all]
            [diegovincent.jaden-case :refer :all]))

(deftest test-quote
  (is (= "How Can Mirrors Be Real If Our Eyes Aren't Real"
         (jaden-case "How can mirrors be real if our eyes aren't real"))))