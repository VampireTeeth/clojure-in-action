(ns clojure-in-action.concurrency (:gen-class))

(def all-users (ref {}))
(def agent-log (agent "LOG:"))
(def all-users-change-counter 0)
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
          user (new-user (inc current-number) login budget-amount)
          tn (.getName (Thread/currentThread))]
      (print (format "%s:%s\r\n" tn "Trying to update ref"))
      (send agent-log log-message (format "[%s]%s" tn "Altering all-users"))
      (alter all-users assoc login user)
    )
  )
)

(defn on-change [the-key the-ref old-val new-val]
  (def all-users-change-counter (inc all-users-change-counter))
)
(add-watch all-users :all-users-watch on-change)

(defn long-calculation [num1 num2]
  (Thread/sleep 2000)
  (* num1 num2)
)

(defn long-run []
  (let [x (long-calculation 11 13)
        y (long-calculation 13 14)
        z (long-calculation 17 19)]
    (* x y z)
  )
)

(defn fast-run []
  (let [x (future (long-calculation 11 13))
        y (future (long-calculation 13 14))
        z (future (long-calculation 17 19))]
    (* @x @y @z)
  )
)

(defn promised-calculation [p]
  (deliver p (long-calculation 11 13))
)
