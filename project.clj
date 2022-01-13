(defproject server "0.1.0-SNAPSHOT"
  :description "Scramblies Challenge"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [ring "1.9.4"]
                 [compojure "1.6.2"]
                 [org.clojure/data.json "2.4.0"]
                 [hiccup "1.0.5"]]
  :min-lein-version "2.0.0"
  :uberjar-name "server-standalone.jar"
  :repl-options {:init-ns server.core}
  :main server.core
  ;; :profiles {:production {:env {:production true}}}
   :profiles {:dev {:main server.core/-dev-main}
              :uberjar {:aot :all}}

  )
