(ns clojure-in-action.macros (:gen-class))

(defmacro sync-set [r v]
  `(dosync (print
            (format "%s:%s\r\n"
             (.getName (Thread/currentThread))
              "Trying to update ref"))
           (ref-set ~r ~v))
)

(defmacro unless [test & exprs]
  `(if (not ~test) (do ~@exprs))
)

(defn exhibits-oddity? [x]
  (unless (even? x)
    (println x "is odd"))
)

(defn exhibits-oddity-verbose? [x]
  (unless (even? x)
    (println x "is odd")
    (println x "is odd again")
  )
)
