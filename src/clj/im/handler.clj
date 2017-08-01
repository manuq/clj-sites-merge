(ns im.handler
  (:require [compojure.core :refer [GET routes defroutes]]
            [compojure.route :refer [not-found resources]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [hiccup.page :refer [include-js include-css html5]]))

(def bfa-page
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    ;;(include-css "/css/site.css")
    ]
   [:body
    [:div#app
     [:h2#change-me "Changing..."]]
    (include-js "/bfa/js/compiled/bfa.js")
    ]))

(defroutes bfa-routes
  (GET "/" [] bfa-page)
  (GET "/index.html" [] bfa-page)
  (resources "/"))

(def bfa-app
  (routes
   (-> bfa-routes
       (wrap-defaults site-defaults)
       wrap-reload)))

;;;;

(def misc-page
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    ;;(include-css "/css/site.css")
    ]
   [:body
    [:div#app
     [:h2#change-me "Changing..."]]
    (include-js "/misc/js/compiled/misc.js")
    ]))

(defroutes misc-routes
  (GET "/" [] misc-page)
  (GET "/index.html" [] misc-page)
  (resources "/"))

(def misc-app
  (routes
   (-> misc-routes
       (wrap-defaults site-defaults)
       wrap-reload)))
