(ns clojure-in-action.concurrency (:gen-class))

(def all-users (ref {}))
(def agent-log (agent "LOG:"))
(defn new-user [id login monthly-budget]
  {
   :id id
   :login login
   :monthly-budget monthly-budget
   :total-expenses 0
   }
)

(defn log-message [prefix message]
  (println prefix message)
  prefix
)
(defn add-new-user [login budget-amount]
  (dosync
    (let [current-number (count @all-users)
          user (new-user (inc current-number) login budget-amount)]
      (println "Trying to update ref")
      (send agent-log log-message "Altering all-users")
      (alter all-users assoc login user)
    )
  )
)
