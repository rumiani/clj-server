(ns server.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [server.view :refer [notFoundComponent]]
            [server.handler :refer [scrambleHandler]]
            ))
(defroutes app
  (GET "/" [] "nothing is here yet.")
  (GET "/scramble/:str1/:str2" [] scrambleHandler)
  (not-found (notFoundComponent)))

(defn -main [port]
  (jetty/run-jetty app
                   {:port (Integer. port)}))
(defn -dev-main [port]
  (jetty/run-jetty (wrap-reload #'app) {:port (Integer. port)}))