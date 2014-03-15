(ns clojure-in-action.date-operations-spec (:gen-class)
  (:use clojure-in-action.date-operations)
  (:use clojure.test)
)

(deftest test-simple-data-parsing
  (let [d (date "2009-01-22")]
    (is (= (day-from d) 22))
    (is (= (year-from d) 2009))
    (is (= (month-from d) 1))
  )
)

(deftest test-as-string
  (let [d (date "2009-01-22")]
    (is (= (as-string d) "2009-01-22"))
  )
)

(deftest test-increment-day
  (let [d (date "2009-10-31")
        n-day (increment-day d)]
    (is (= (as-string n-day) "2009-11-01"))
  )
)

(deftest test-increment-date
  (let [d (date "2009-10-31")
        n-day (increment-day d)
        n-month (increment-month d)
        n-year (increment-year d)]
    (is (= (as-string n-day) "2009-11-01"))
    (is (= (as-string n-month) "2009-11-30"))
    (is (= (as-string n-year) "2010-10-31"))
  )
)
(deftest test-decrement-date
  (let [d (date "2009-10-31")
        p-day (decrement-day d)
        p-month (decrement-month d)
        p-year (decrement-year d)
        d1 (date "2009-11-01")
        p-day1 (decrement-day d1)]
    (is (= (as-string p-day) "2009-10-30"))
    (is (= (as-string p-month) "2009-09-30"))
    (is (= (as-string p-year) "2008-10-31"))
    (is (= (as-string p-day1) "2009-10-31"))
  )
)
