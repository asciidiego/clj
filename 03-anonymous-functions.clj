;; In addition to named functions with `defn`, you can also create anonymous
;; functions with `fn`. There are (at least) three reasons to create anonymous
;; functions:
;;
;; - The function is brief and self-explanatory. Giving it a name makes the
;;   harder to read, not easier.
;; - The function is being used only from inside another function and needs
;;   a local name, not a top-level binding.
;; - The function is created inside another function for the purpose of
;;   capturing the values of parameters or local bindings.
;;
;; Brief and self-explanatory functions
;; ====================================
;; Take an example where you want to crneate an index for a sequence of words,
;; and you want to filter out words shorter than 3 characters. You can create
;; a function as follows:

(defn indexable-word? [word]
  (> (count word) 2))

(def words ["yes" "no"])
(println (filter indexable-word? words))
; -> (yes)

;; Anonymous functions let you do the same thing in a single line. The simplest
;; anonymous `fn` form is the folowing:
;;
;; (fn [params*] body)
;;
;; With this form, you can plug the implementation in a single line as follows:
;; Print words whose char length is > 2
(println (filter (fn [word] (> (count word) 2)) words))

;; There's even a shorter reader macro syntax for anonymous functions. To make
;; it even shorter, we can use implicit parameters:

(println (filter #(> (count %) 2) words))

;; In that case, "%" refers to the first parameter. Yet, we can use many
;; positional arguments, by using %1, %2... and so on. Optionally, you can use
;; the final %& to collect the **rest** of the arguments when they vary in length.
;; The syntax for this subset of anonymous functions is:
;; 
;; #(body)
;;
;; Nested functions
;; ================
;;
;; What about when it is in your best interest to use a function inside another
;; function? This is our second use-case.
;;
;; Let's take the earlier example as a test. That is, the indexable-words example.
;; By using the `let` function, we can evaluate an expression inside the lexical
;; scope of another function

(defn indexable-words [text]
  (let [indexable-word? #(> (count %) 2)]
    (filter indexable-word? text)))

(println (indexable-words words))

;; The `let` binds the name `indexable-word?` to the same anonymous function from
;; earlier. This time, however, inside the lexical scope of `indexable-words`.
;;
;; The combination of `let` and an anonoymous function tha thas a name tells the
;; reader the following:
;;
;; > The function `indexable-word?` is interesting enough to have a name. But only
;;   inside the `indexable-words` function.
;;
;; Creating dynamic functions at run-time
;; ======================================
;;
;; In this case, we can use `fn` or the `#(body)` macro to create anonymous
;; functions that create other functions. For example, let's create hello worlds
;; that have different prefix-ers or hello-ers:
;;
(defn make-hello-world
  ([] (fn [person] (str "Hello, " person)))
  ([prefix] (fn [person] (str prefix ", " person))))

;; (def standard-hello (make-hello-world))
;; -> #'user/standard-hello
;; (def hawaian-hello (make-hello-world "Aloha"))
;; -> #'user/hawaian-hello
;;
;; Now, you can call these functions, like any other function:
;; (standard-hello "World")
;; -> "Hello, World"
;; (hawain-hello "World")
;; -> "Aloha, World"

;; Moreover, you can use an immediately created function by putting it in the
;; first slot of a form:
;;
;; ((make-hello-world "Hi") "World")
;; -> "Hello, World"
;;
;; As you can see, the different functions *remember* the value of `prefix` at
;; creation time. More formally, each function are **closures** over the value of
;; `prefix`
;;
;; Conclusion
;; ==========
;;
;; So, when do you use anonymous functions? Their syntax is terse, perhaps too
;; much at times. Even more if you use the `#(body)` reader macro.
;;
;; All in all, they are an option--not a requirement. Use them when you find that
;; they make your code more readable. It takes some time until they become
;; familiar.
