(defproject pruebajoin "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [org.clojure/core.async  "0.3.442"
                  :exclusions [org.clojure/tools.reader]]
                 [http-kit "2.2.0"]
                 [ring "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [compojure "1.6.0"]
                 [hiccup "1.0.5"]]

  :plugins [[lein-figwheel "0.5.10"]
            [lein-cljsbuild "1.1.5" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src/clj"]
  :resource-paths ["resources"]

  :main im.server

  :cljsbuild {:builds {

              :bfa-dev {
                ;;:resource-paths ["resources/bfa"]
                :source-paths ["src/cljs"]

                ;; the presence of a :figwheel configuration here
                ;; will cause figwheel to inject the figwheel client
                ;; into your build
                :figwheel {:on-jsload "bfa.core/on-js-reload"
                           :open-urls ["http://localhost:3449/"]}

                :compiler {:main bfa.core
                           :asset-path "bfa/js/compiled/out"
                           :output-to "resources/public/bfa/js/compiled/bfa.js"
                           :output-dir "resources/public/bfa/js/compiled/out"
                           :source-map-timestamp true
                           ;; To console.log CLJS data-structures make sure you enable devtools in Chrome
                           ;; https://github.com/binaryage/cljs-devtools
                           :preloads [devtools.preload]}}

              ;;  ;; This next build is an compressed minified build for
              ;;  ;; production. You can build this with:
              ;;  ;; lein cljsbuild once min
              ;; :bfa-min {
              ;;   ;; :resource-paths ["resources/bfa"]
              ;;   :source-paths ["src/cljs"]
              ;;   :compiler {:main bfa.core
              ;;              :output-to "resources/public/bfa/js/compiled/bfa.min.js"
              ;;              :optimizations :advanced
              ;;              :pretty-print false}}


              :im-dev {
                ;; :resource-paths ["resources/misc"]
                :source-paths ["src/cljs"]

                ;; the presence of a :figwheel configuration here
                ;; will cause figwheel to inject the figwheel client
                ;; into your build
                :figwheel {:on-jsload "im.core/on-js-reload"
                           :open-urls ["http://localhost:3449/"]}

                :compiler {:main im.core
                           :asset-path "im/js/compiled/out"
                           :output-to "resources/public/im/js/compiled/im.js"
                           :output-dir "resources/public/im/js/compiled/out"
                           :source-map-timestamp true
                           ;; To console.log CLJS data-structures make sure you enable devtools in Chrome
                           ;; https://github.com/binaryage/cljs-devtools
                           :preloads [devtools.preload]}}

              ;;  ;; This next build is an compressed minified build for
              ;;  ;; production. You can build this with:
              ;;  ;; lein cljsbuild once min
              ;; :im-min {
              ;;   ;; :resource-paths ["resources/misc"]
              ;;   :source-paths ["src/cljs"]
              ;;   :compiler {:main im.core
              ;;              :output-to "resources/public/im/js/compiled/im.min.js"
              ;;              :optimizations :advanced
              ;;              :pretty-print false}}

}}

  :figwheel {;; :http-server-root "public" ;; default and assumes "resources"
             ;; :server-port 3449 ;; default
             ;; :server-ip "127.0.0.1"


             ;; Start an nREPL server into the running figwheel process
             ;; :nrepl-port 7888

             ;; Server Ring Handler (optional)
             ;; if you want to embed a ring handler into the figwheel http-kit
             ;; server, this is for simple ring servers, if this

             ;; doesn't work for you just run your own server :) (see lein-ring)

             ;; For BFA
             :css-dirs ["resources/public/bfa/css"]
             :ring-handler im.bfa-handler/app
             :server-logfile "figwheel-logfile-bfa.log"

             ;; For MISC
            ;; :css-dirs ["resources/public/im/css"]
            ;; :ring-handler im.handler/app
            ;; :server-logfile "figwheel-logfile-im.log"

             ;;:load-all-builds false 

             ;; if you want to disable the REPL
             ;; :repl false

             ;; to pipe all the output to the repl
             ;; :server-logfile false
             }


  ;; setting up nREPL for Figwheel and ClojureScript dev
  ;; Please see:
  ;; https://github.com/bhauman/lein-figwheel/wiki/Using-the-Figwheel-REPL-within-NRepl
  :profiles {
             :dev {:dependencies [[binaryage/devtools "0.9.2"]
                                  [figwheel-sidecar "0.5.10"]
                                  [com.cemerick/piggieback "0.2.1"]]
                   ;; need to add dev source path here to get user.clj loaded
                   :source-paths ["src/cljs" "dev"]
                   ;; for CIDER
                   ;; :plugins [[cider/cider-nrepl "0.12.0"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   ;; need to add the compliled assets to the :clean-targets
                   :clean-targets ^{:protect false} ["resources/public/bfa/js/compiled"
                                                     "resources/public/im/js/compiled"
                                                     :target-path]}})
