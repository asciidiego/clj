(ns diegovincent.jaden-case
  (:require [clojure.string :refer [split join]]))

(defn jaden-case
  "Return strings like Jaden Smith. That is, capitalizes each word"
  [x] (->> (split x #" ")
           (map #(clojure.string/capitalize %))
           (join " ")))
