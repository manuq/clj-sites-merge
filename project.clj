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
                 [hiccup "1.0.5"]
                 [lein-garden "0.3.0"]]

  :plugins [[lein-figwheel "0.5.10"]
            [lein-cljsbuild "1.1.5" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src/clj"]
  ;; fixme needed?
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
                           :asset-path "js/compiled/out"
                           :output-to "resources/bfa/public/js/compiled/bfa.js"
                           :output-dir "resources/bfa/public/js/compiled/out"
                           :source-map-timestamp true
                           ;; To console.log CLJS data-structures make sure you enable devtools in Chrome
                           ;; https://github.com/binaryage/cljs-devtools
                           :preloads [devtools.preload]}}

               ;; This next build is an compressed minified build for
               ;; production. You can build this with:
               ;; lein cljsbuild once min
              :bfa-min {
                ;; :resource-paths ["resources/bfa"]
                :source-paths ["src/cljs"]
                :compiler {:main bfa.core
                           :output-to "resources/bfa/public/js/compiled/bfa.min.js"
                           :output-dir "resources/bfa/public/js/compiled-min/out"
                           :optimizations :advanced
                           :pretty-print false}}


              :im-dev {
                ;; :resource-paths ["resources/misc"]
                :source-paths ["src/cljs"]

                ;; the presence of a :figwheel configuration here
                ;; will cause figwheel to inject the figwheel client
                ;; into your build
                :figwheel {:on-jsload "im.core/on-js-reload"
                           :open-urls ["http://localhost:3449/"]}

                :compiler {:main im.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/im/public/js/compiled/im.js"
                           :output-dir "resources/im/public/js/compiled/out"
                           :source-map-timestamp true
                           ;; To console.log CLJS data-structures make sure you enable devtools in Chrome
                           ;; https://github.com/binaryage/cljs-devtools
                           :preloads [devtools.preload]}}

               ;; This next build is an compressed minified build for
               ;; production. You can build this with:
               ;; lein cljsbuild once min
              :im-min {
                ;; :resource-paths ["resources/misc"]
                :source-paths ["src/cljs"]
                :compiler {:main im.core
                           :output-to "resources/im/public/js/compiled/im.min.js"
                           :output-dir "resources/im/public/js/compiled-min/out"
                           :optimizations :advanced
                           :pretty-print false}}

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
             :fig-im {:figwheel {:http-server-root "im/public"
                                 :css-dirs ["resources/im/public/css"]
                                 :ring-handler im.handler/app
                                 :server-logfile "figwheel-logfile-im.log"}
                      :clean-targets ^{:protect false} ["resources/im/public/js/compiled"
                                                        :target-path]}
             :fig-bfa {:figwheel {:http-server-root "bfa/public"
                                  :css-dirs ["resources/bfa/public/css"]
                                  :ring-handler im.bfa-handler/app
                                  :server-logfile "figwheel-logfile-bfa.log"}
                       :clean-targets ^{:protect false} ["resources/bfa/public/js/compiled"
                                                         :target-path]}
             :dev {:dependencies [[binaryage/devtools "0.9.2"]
                                  [figwheel-sidecar "0.5.10"]
                                  [com.cemerick/piggieback "0.2.1"]]
                   ;; need to add dev source path here to get user.clj loaded
                   :source-paths ["src/cljs" "dev"]
                   ;; for CIDER
                   ;; :plugins [[cider/cider-nrepl "0.12.0"]]
                   :plugins [[lein-garden "0.3.0"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

                   :garden {:builds [{:source-paths ["src/styles"]
                                      :stylesheet im.style/screen
                                      :compiler {:output-to "resources/im/public/css/screen.css"
                                                 :pretty-print? false}}
                                     {:source-paths ["src/styles"]
                                      :stylesheet bfa.style/screen
                                      :compiler {:output-to "resources/bfa/public/css/screen.css"
                                                 :pretty-print? false}}]}

                   }


             })


             ;; })
