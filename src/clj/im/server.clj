(ns im.server
  (:use [org.httpkit.server :only [run-server]])
  (:require [im.handler]
            [im.bfa-handler]))

(defn -main [& args]
  (let [port (Integer. (nth args 0 "3000"))]
    (case port
      3000 (run-server im.handler/app {:port port})
      3001 (run-server im.bfa-handler/app {:port port}))
    (println "serving site on port" port)))
