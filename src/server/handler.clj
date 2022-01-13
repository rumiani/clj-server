(ns server.handler
  (:require
   [clojure.data.json :as json]
   [server.view :refer [ main-page-component]]
))
(defn scramble?
  [str1 str2]
  (every? (fn [[l f]] (<= f (get (frequencies str1) l 0))) (frequencies str2)))

(defn scramble-handler  [req]
  (let [str1 (get-in req [:route-params :str1]) str2 (get-in req [:route-params :str2])]
    {:status 200
     :body   (json/write-str (scramble? str1 str2))
     :header {}}))

(defn main-page-handler []
  {:status 200
   :body   (main-page-component)
   :header {}}
  )