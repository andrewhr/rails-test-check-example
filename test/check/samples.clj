(ns check.samples
  (:require [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :refer [for-all]]
            [clojure.test.check.generators :as gen]))

(def number-of-specs 100)

(defspec addition-identity-is-zero number-of-specs
  (for-all [a gen/int]
    (= a (+ a 0))))

(defspec addition-is-commutative number-of-specs
  (for-all [a gen/int
            b gen/int
            c gen/int]
    (= (+ a b c) (+ a c b))))

(defspec addition-is-associative number-of-specs
  (for-all [a gen/int
            b gen/int
            c gen/int]
    (= (+ (+ a b) c) (+ a (+ b c)))))

(defspec sort-is-ascending number-of-specs
  (for-all [v (gen/vector gen/int)]
    (let [sorted (sort v)]
      (every? (fn [[a b]] (<= a b)) (partition 2 1 sorted)))))

(defspec sort-is-idempotent number-of-specs
  (for-all [v (gen/vector gen/int)]
    (= (sort v) (sort (sort v)))))
