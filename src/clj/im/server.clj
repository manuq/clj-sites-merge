(ns im.server
  (:use [org.httpkit.server :only [run-server]])
  (:require [im.handler :refer [bfa-app misc-app]]))

(defn -main [& args]
  (run-server bfa-app {:port 8080}))
