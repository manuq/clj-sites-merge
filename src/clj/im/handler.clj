(ns im.handler
  (:require [compojure.core :refer [GET routes defroutes]]
            [compojure.route :refer [resources]]
            [ring.middleware.reload :refer [wrap-reload]]
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
  (GET "/" [] #(:page-template %))
  ;; FIXME (resources "/" #(:resources-template %))
  (resources "/" {:root "im/public"})
  (resources "/" {:root "bfa/public"}))


(defn get-page-template [server-name]
  (let [page-for-name {"bfa.local" bfa-page
                       "misc.local" im-page}]
    (get page-for-name server-name)))

(defn get-res-template [server-name]
  (let [res-for-name {"bfa.local" {:root "bfa/public"}
                      "misc.local" {:root "im/public"}]
    (get res-for-name server-name)))

(defn wrap-serve-template
  [handler]
  (fn [request]
    (let [server-name (:server-name request)
          page-template (get-page-template server-name)
          res-template (get-res-template server-name)]
      (handler (assoc request
                      :page-template page-template
                      :resources-template res-template)))))

(def app
  (routes
   (-> app-routes
       (wrap-defaults site-defaults)
       wrap-reload
       wrap-serve-template)))
