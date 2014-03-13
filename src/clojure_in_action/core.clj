(ns clojure-in-action.core
  (:gen-class)
  (:use clojure-in-action.metadata-example)
  (:use clojure-in-action.multimethod-example)
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (println untrusted)
  (println (meta untrusted))
  (println still-suspect)
  (println (meta still-suspect))
  (testing-meta)
  (println (meta (var testing-meta)))
  (println "Steven has to pay" (affiliate-fee {:salary 90000 :name "Steven" :referrer :google.com}) "dollars")
  (println "Andrew has to pay" (affiliate-fee {:salary 85000 :name "Andrew" :referrer :mint.com}) "dollars")
  (println "Julius has to pay" (affiliate-fee {:salary 82000 :name "Julius"}) "dollars")

  (println "Ashley has to pay" (profit-based-affiliate-fee {:salary 82000 :name "Ashley" :referrer :mint.com}) "dollars")

  (println "Jason has to pay" (affiliate-fee-for-hierarchy {:salary 92000 :name "Jason" :referrer :mint.com}) "dollars")
)