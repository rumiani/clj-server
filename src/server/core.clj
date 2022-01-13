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

;; (defn -main [port]
;;   (jetty/run-jetty app                 {:port (Integer. port)}))
;; (defn -main [port]
;;   (println "####THE VALUE OF ENV VAR PORT IS" (System/getenv "PORT"))
;;   (flush)    ; Force that output to be written to the Heroku log, before we move on to (trying to) start Jetty
;;   (jetty/run-jetty app                 {:port (Integer. port)}))

(defn -main [& args]
  (println "####THE VALUE OF ENV VAR PORT IS" (System/getenv "PORT"))
  (flush)


)

















(defn -dev-main [port]
  (jetty/run-jetty (wrap-reload #'app) {:port (Integer. port)}))
