(ns server.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]
            [server.handler :refer [ scramble-handler main-page-handler ] ]
            [server.view :refer [ not-found-component] ]
            ))

(defroutes app
  (GET "/request" [] handle-dump)
  (GET "/" [] main-page-handler)
  (GET "/scramble/:str1/:str2" [] scramble-handler)
  (not-found (not-found-component)))

(defn -main []
  (jetty/run-jetty app                 {:port (Integer. 8000)}))
(defn -dev-main []
  (jetty/run-jetty (wrap-reload #'app) {:port (Integer. 8000)}))