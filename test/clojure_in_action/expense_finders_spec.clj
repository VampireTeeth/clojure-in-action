(ns clojure-in-action.expense-finders-spec (:gen-class)
    (:use clojure.test)
    (:use clojure-in-action.expense-finders)
    (:use clojure-in-action.stubbing))

(def all-expenses [(struct-map expense :amount 10.0 :date "2010-02-28")
                   (struct-map expense :amount 16.0 :date "2010-03-15")
                   (struct-map expense :amount 21.0 :date "2014-02-15")])

(deftest test-fetch-expenses-greater-than
  (stubbing [fetch-all-expenses all-expenses]
    (let [filtered (fetch-expenses-greater-than "" "" "" 15.0)]
      (is (= (count filtered) 2))
      (is (= (:amount (first filtered)) 16.0))
      (is (= (:amount (last filtered)) 21.0))
    )
  )
)

(deftest test-expenses-greater-than
  (let [filtered (expense-greater-than all-expenses 15.0)]
    (is (= (count filtered) 2))
    (is (= (:amount (first filtered)) 16.0))
    (is (= (:amount (last filtered)) 21.0))
  )
)
