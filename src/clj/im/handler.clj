(ns im.handler
  (:require [compojure.core :refer [GET routes defroutes]]
            [compojure.route :refer [resources]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.defaults :refer [wrap-defaults
                                              site-defaults]]
            [hiccup.page :refer [include-js include-css html5]]))

(def main-page
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    (include-css "/css/screen.css")
    ]
   [:body
    [:div#app
     [:h2#change-me "Changing..."]]
    (include-js "/js/compiled/im.js")
    ]))

(defroutes app-routes
  (GET "/" [] main-page)
  (resources "/" {:root "im/public"}))

(def app
  (routes
   (-> app-routes
       (wrap-defaults site-defaults)
       wrap-reload)))
