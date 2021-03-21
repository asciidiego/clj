;; Clojure functions enforce their arity. It is the expected number of arguments
;; for a given function. If you call a function with the incorrect number nof
;; arguments, Clojure throws an ArityException.
;;

(defn say-hello
  "Says hello to a person.
   If no person is specified, says hello to the world."
  ([] (say-hello "World"))
  ([person] (str "Hello, " person "!"))
  ([person-1 person-2] (str "Hello, " person-1 " and " person-2 "!")))  
