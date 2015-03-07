(defproject rails-test-check-example "0.1.0-SNAPSHOT"
  :description "Suite demoing generative tests"
  :url "http://github.com/andrewhr/rails-property-test-example"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/test.check "0.7.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [postgresql/postgresql "9.1-901.jdbc4"]
                 [clj-webdriver "0.6.1"]
                 [clj-http "1.0.1"]])
