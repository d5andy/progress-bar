(ns progress-bar.core-test
  (:require [clojure.test :refer :all])
  (:require [progress-bar.core :refer :all]))

(println "dada")

(let [wait-for-it (future (Thread/sleep 10000) (println "done") 100)]
  (Thread/sleep 250)
  (println "waiting")
  (cond
    (future-done? wait-for-it) (println @wait-for-it)
    :else (recur wait-for-it))
  )



(deftest callToPrint
  (testing "after some time message is printed"
    (is (= 0 1))))


(run-tests)
