(ns server.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]
            [server.handler :refer [ scramble-handler main-page-handler ] ]
            [server.view :refer [ not-found-component] ]
            )
  (:gen-class))

(defroutes app
  (GET "/request" [] handle-dump)
  (GET "/" [] main-page-handler)
  (GET "/scramble/:str1/:str2" [] scramble-handler)
  (not-found (not-found-component)))

;; (defn -main [port]
;;   (jetty/run-jetty app                 {:port (Integer. port)}))
;; (defn -main [port]
;;   (println "####THE VALUE OF ENV VAR PORT IS" (System/getenv "PORT"))
;;   (flush)    ; Force that output to be written to the Heroku log, before we move on to (trying to) start Jetty
;;   (jetty/run-jetty app                 {:port (Integer. port)}))

;; (defn -main [& args]
;;   (println "####THE VALUE OF ENV VAR PORT IS" (System/getenv "PORT"))
;;   (flush)
;; )
;; (defn -main [& args]
;;   (println "####THE VALUE OF ENV VAR PORT IS" (System/getenv "PORT"))
;;   (flush)    ; Force that output to be written to the Heroku log, before we move on to (trying to) start Jetty
;;   (let [port (first args)]
;;     (jetty/run-jetty app {:port (Integer. port)})))

(defn -main [& args]
  (let [port (s/trim (first args))]    ; Note: you may need to add a require on [clojure.string :as s] to your ns declaration for this to work
    (println "Starting HTTP server on port" port)
    (flush)    ; Force that output to be written to the Heroku log, before we move on to (trying to) start Jetty
    (jetty/run-jetty app {:port (Integer/parseInt port)})))
















(defn -dev-main [port]
  (jetty/run-jetty (wrap-reload #'app) {:port (Integer. port)}))
