(ns server.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]
            [server.handler :refer [ scramble-handler main-page-handler ] ]
            [server.view :refer [ not-found-component] ]
            [clojure.string :as s]
            )
  (:gen-class))

(defroutes app
  (GET "/request" [] handle-dump)
  (GET "/" [] main-page-handler)
  (GET "/scramble/:str1/:str2" [] scramble-handler)
  (not-found (not-found-component)))

;; (defn -main [port]
;;   (jetty/run-jetty app                 {:port (Integer. port)}))

(defn -main [& args]
  (println "####TEST!!!! args has" (count args) "elements, and is" args)
  (flush)
  (let [port (Integer/parseInt (s/trim (first args)))]
    (println "Starting HTTP server on port" port)
    (flush)
    (jetty/run-jetty app {:port port})))


















(defn -dev-main [port]
  (jetty/run-jetty (wrap-reload #'app) {:port (Integer. port)}))
