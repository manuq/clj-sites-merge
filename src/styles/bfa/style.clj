(ns bfa.style
  (:refer-clojure :exclude [+ - * / rem])
  (:require [garden.def :refer [defstyles defstylesheet]]
            [garden.units :refer :all]
            [garden.core :refer [css]]
            [garden.color :refer [hsl rgb hex->rgb]]
            [garden.arithmetic :refer [+ - * /]]
            [garden.stylesheet :refer [at-media]]
            [clojure.string :as str]
            [common.style :refer [common-styles]]))

(def bfa-styles
  [[:h2#change-me {:background-color "#ffd"}]]
  )

(def screen (reduce concat [common-styles bfa-styles]))
