(ns progress-bar.core
  (:require [clj-progress.core :refer :all :as prog]))

(set-progress-bar! ":header [:wheel] :elapsed")

(defn perform-with-progress-bar [fc msg]
  (prog/init msg -1)
  (loop [wait-for-it (future-call fc)]
    (prog/tick)
    (cond
      (future-done? wait-for-it) (do
                                   (prog/done)
                                   (println @wait-for-it))
      :else (recur wait-for-it))))

(defn -main [& args]
  (perform-with-progress-bar #(do ( Thread/sleep 10000) (println "done") 100) "fds"))
