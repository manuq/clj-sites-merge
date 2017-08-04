(ns im.handler
  (:require [compojure.core :refer [GET routes defroutes]]
            [compojure.route :refer [resources]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.file :refer [wrap-file]]
            [ring.middleware.defaults :refer [wrap-defaults
                                              site-defaults]]
            [hiccup.page :refer [include-js include-css html5]]))

(def im-page
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

(def bfa-page
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    (include-css "/css/screen.css")
    ]
   [:body
    [:div#app
     [:h2#change-me "Changing..."]]
    (include-js "/js/compiled/bfa.js")
    ]))

(defroutes app-routes
  (GET "/" [] #(:page-template %)))

(defn get-page-template [server-name]
  (let [page-for-name {"bfa.local" bfa-page
                       "misc.local" im-page}]
    (get page-for-name server-name)))

(defn get-resource-template [server-name]
  (let [res-for-name {"bfa.local" "resources/bfa/public"
                      "misc.local" "resources/im/public"}]
    (get res-for-name server-name)))

(defn wrap-serve-template [handler]
  (fn [request]
    (let [server-name (:server-name request)
          template (get-page-template server-name)]
      (handler (assoc request :page-template template)))))

(defn wrap-file-template [handler]
  (fn [request]
    (let [server-name (:server-name request)
          res-template (get-resource-template server-name)]
      ((wrap-file handler res-template) request))))

(def app
  (routes
   (-> app-routes
       (wrap-defaults site-defaults)
       wrap-reload
       wrap-serve-template
       wrap-file-template)))
