(ns misc.core
  (:require
   [goog.dom :as dom]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello world!"}))

(set! (.-innerText (dom/getElement "change-me")) "MISC")

(defn on-js-reload []
)
