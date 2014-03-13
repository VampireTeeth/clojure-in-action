(ns clojure-in-action.multimethod-example (:gen-class))

(derive ::bronze ::basic)
(derive ::silver ::basic)
(derive ::gold ::premier)
(derive ::platinum ::premier)


(defn fee-amount [percentage user]
  (float (* 0.01 percentage (:salary user)))
)

(defn affiliate-fee-cond
  "Calculation of affilate fee without multimethod"
  [user]
  (cond
    (= :google.com (:referrer user)) (fee-amount 0.01 user)
    (= :mint.com (:referrer user)) (fee-amount 0.03 user)
    :default (fee-amount 0.02 user)
  )
)

(defmulti affiliate-fee :referrer)
(defmethod affiliate-fee :google.com [user]
  (fee-amount 0.01 user)
)
(defmethod affiliate-fee :mint.com [user]
  (fee-amount 0.03 user)
)
(defmethod affiliate-fee :default [user]
  (fee-amount 0.02 user)
)

(defn profit-rating [user]
  (let [ratings [::bronze ::silver ::gold ::platinum]]
    (nth ratings (rand-int (count ratings)))
  )
)

(defn fee-category [user]
  [(:referrer user) (profit-rating user)]
)

(defmulti profit-based-affiliate-fee fee-category)
(defmethod profit-based-affiliate-fee [:mint.com ::bronze] [user]
  (fee-amount 0.03 user)
)
(defmethod profit-based-affiliate-fee [:mint.com ::silver] [user]
  (fee-amount 0.04 user)
)
(defmethod profit-based-affiliate-fee [:mint.com ::gold] [user]
  (fee-amount 0.05 user)
)
(defmethod profit-based-affiliate-fee [:mint.com ::platinum] [user]
  (fee-amount 0.05 user)
)
(defmethod profit-based-affiliate-fee [:google.com ::gold] [user]
  (fee-amount 0.03 user)
)
(defmethod profit-based-affiliate-fee [:google.com ::platinum] [user]
  (fee-amount 0.03 user)
)
(defmethod profit-based-affiliate-fee :default [user]
  (fee-amount 0.02 user)
)

(defmulti affiliate-fee-for-hierarchy fee-category)
(defmethod affiliate-fee-for-hierarchy [:mint.com ::bronze] [user]
  (fee-amount 0.03 user)
)
(defmethod affiliate-fee-for-hierarchy [:mint.com ::silver] [user]
  (fee-amount 0.04 user)
)
(defmethod affiliate-fee-for-hierarchy [:mint.com ::premier] [user]
  (fee-amount 0.05 user)
)
(defmethod affiliate-fee-for-hierarchy [:google.com ::premier] [user]
  (fee-amount 0.03 user)
)
(defmethod affiliate-fee-for-hierarchy :default [user]
  (fee-amount 0.02 user)
)
