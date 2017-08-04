(defproject sites-merge "0.1.0-SNAPSHOT"
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

  :plugins [[lein-cljsbuild "1.1.5" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src/clj"]

  :main im.server
  :aliases {"fig-all" ["figwheel" "im-dev" "bfa-dev"]}

  :cljsbuild {:builds {:im-dev {:source-paths ["src/cljs"]
                                :figwheel {:on-jsload "im.core/on-js-reload"}
                                :compiler {:main im.core
                                           :asset-path "js/compiled/out"
                                           :output-to "resources/im/public/js/compiled/im.js"
                                           :output-dir "resources/im/public/js/compiled/out"
                                           :source-map-timestamp true
                                           :preloads [devtools.preload]}}
                       :im-min {:source-paths ["src/cljs"]
                                :compiler {:main im.core
                                           :output-to "resources/im/public/js/compiled/im.min.js"
                                           :output-dir "resources/im/public/js/compiled-min/out"
                                           :optimizations :advanced
                                           :pretty-print false}}
                       :bfa-dev {:source-paths ["src/cljs"]
                                 :figwheel {:on-jsload "bfa.core/on-js-reload"}
                                 :compiler {:main bfa.core
                                            :asset-path "js/compiled/out"
                                            :output-to "resources/bfa/public/js/compiled/bfa.js"
                                            :output-dir "resources/bfa/public/js/compiled/out"
                                            :source-map-timestamp true
                                            :preloads [devtools.preload]}}
                       :bfa-min {:source-paths ["src/cljs"]
                                 :compiler {:main bfa.core
                                            :output-to "resources/bfa/public/js/compiled/bfa.min.js"
                                            :output-dir "resources/bfa/public/js/compiled-min/out"
                                            :optimizations :advanced
                                            :pretty-print false}}}}

  :profiles {:default [:base :system :user :provided :dev]
             :dev {:dependencies [[binaryage/devtools "0.9.2"]
                                  [figwheel-sidecar "0.5.10"]
                                  [com.cemerick/piggieback "0.2.1"]
                                  [lein-garden "0.3.0"]]

                   :figwheel {:css-dirs ["resources/im/public/css"
                                         "resources/bfa/public/css"]}

                   :clean-targets ^{:protect false} ["resources/im/public/js/compiled"
                                                     "resources/bfa/public/js/compiled"
                                                     :target-path]

                   :source-paths ["src/cljs" "dev"]

                   :plugins [[lein-figwheel "0.5.10"]
                             [lein-garden "0.3.0"]
                             [cider/cider-nrepl "0.12.0"]]

                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

                   :garden {:builds [{:id "im"
                                      :source-paths ["src/styles"]
                                      :stylesheet im.style/screen
                                      :compiler {:output-to "resources/im/public/css/screen.css"
                                                 :pretty-print? false}}
                                     {:id "bfa"
                                      :source-paths ["src/styles"]
                                      :stylesheet bfa.style/screen
                                      :compiler {:output-to "resources/bfa/public/css/screen.css"
                                                 :pretty-print? false}}]}}})
