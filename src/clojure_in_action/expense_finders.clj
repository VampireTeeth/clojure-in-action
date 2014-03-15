(ns clojure-in-action.expense-finders (:gen-class)
(:use [clojure.string :only (join)]))

(defstruct expense :amount :date)

(defn log-call [id & args]
  (println "Audit - called" id "with:" (join ", " args))
  ;;do logging to some audit data-store
)

(defn ^:dynamic fetch-all-expenses [username start-date end-date]
  (log-call "fetch all" username start-date end-date)
  ;;find in data-store, return list of expense structs
)

(defn expense-greater-than [expenses threshold]
  (log-call "expense-greater-than" threshold)
  (filter #(> (:amount %) threshold) expenses)
)

(defn fetch-expenses-greater-than [username start-date end-date threshold]
  (log-call "fetch-expenses-greather-than" threshold)
  (expense-greater-than (fetch-all-expenses username start-date end-date) threshold)
)
