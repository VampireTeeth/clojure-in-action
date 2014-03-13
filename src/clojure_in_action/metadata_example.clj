(ns clojure-in-action.metadata-example (:gen-class))

(def untrusted (with-meta {:command "clean-table" :subject "users"} {:safe false :io true}))

(def still-suspect (assoc untrusted :complete? false))

(defn testing-meta
"testing meta with functions"
{:safe true :console true}
[]
(println "Hello from tesing-meta")
)
