(ns bfa.core
  (:require
   [goog.dom :as dom]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello world!"}))

(set! (.-innerText (dom/getElement "change-me")) "BFA")

(defn on-js-reload []
)
