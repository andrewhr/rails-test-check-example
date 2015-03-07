(ns check.rails
  (:require [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :refer [for-all]]
            [clojure.test.check.generators :as gen]
            [clj-http.client :as http]
            [clojure.java.jdbc :as jdbc]
            [clj-webdriver.taxi :refer :all]))

(def number-of-specs 25)

(def base-url "http://localhost:3000")

(def test-db {:connection-uri "jdbc:postgresql:money_check_test"})

(def add-money-entry-action
  (gen/hash-map :action (gen/return :add-entry)
                :via    (gen/elements [:ui :api])
                :value  gen/int))

(defmulti dispatch! (juxt :action :via))

(defmethod dispatch! [:add-entry :ui] [action]
  (to base-url)
  (input-text "#entry_value" (str (:value action)))
  (submit "#entry_value"))

(defmethod dispatch! [:add-entry :api] [action]
  (http/post (str base-url "/api/entries")
             {:content-type :json
              :form-params  {:entry {:value (:value action)}}}))

(defn dispatch-all! [actions]
  (doseq [action actions]
    (dispatch! action)))

(defn prepare-database! [db-spec]
  (jdbc/execute! db-spec ["TRUNCATE entries"]))

(defn total-of [actions]
  (->> actions (map :value) (reduce + 0)))

(defn total-recorded-in [db-spec]
  (get (first (jdbc/query db-spec "SELECT SUM(value) FROM entries")) :sum 0))

(set-driver! {:browser :phantomjs})

(defspec track-all-money-on-database number-of-specs
  (for-all [actions (gen/not-empty (gen/vector add-money-entry-action))]
    (prepare-database! test-db)
    (dispatch-all! actions)
    (= (total-of actions) (total-recorded-in test-db))))
