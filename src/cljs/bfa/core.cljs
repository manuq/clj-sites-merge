(ns bfa.core
  (:require
   [common.hello :refer [say-hello]]
   [goog.dom :as dom]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello world!"}))

(set! (.-innerText (dom/getElement "change-me")) (say-hello "BFA"))

(defn on-js-reload []
)
