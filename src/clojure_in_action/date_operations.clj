(ns clojure-in-action.date-operations (:gen-class)
    (:import (java.text SimpleDateFormat)
             (java.util Calendar GregorianCalendar))
)

(defn date [date-string]
  (let [sdf (SimpleDateFormat. "yyyy-MM-dd")
        d (.parse sdf date-string)]
    (doto (GregorianCalendar.)
      (.setTime d)
    )
  )
)

(defn day-from [d]
  (.get d Calendar/DAY_OF_MONTH)
)
(defn year-from [d]
  (.get d Calendar/YEAR)
)
(defn month-from [d]
  (inc (.get d Calendar/MONTH))
)

(defn as-string [d]
  (let [sdf (SimpleDateFormat. "yyyy-MM-dd")]
    (.format sdf (.getTime d))
  )
)

(defn date-operator [op f]
  (fn [d]
    (doto (.clone d)
      (.add f (op 1))
    )
  )
)

(def increment-day (date-operator + Calendar/DAY_OF_MONTH))
(def increment-month (date-operator + Calendar/MONTH))
(def increment-year (date-operator + Calendar/YEAR))
(def decrement-day (date-operator - Calendar/DAY_OF_MONTH))
(def decrement-month (date-operator - Calendar/MONTH))
(def decrement-year (date-operator - Calendar/YEAR))
