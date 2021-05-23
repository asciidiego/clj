(ns diegovincent.jaden-case)

(defn jaden-case
  "Return strings like Jaden Smith. That is, capitalizes each word"
  [x] (->> (clojure.string/split x #" ")
           (map #(clojure.string/capitalize %))
           (clojure.string/join " ")))
