(ns server.handler
  (:require
   [clojure.data.json :as json]
))

(defn scramble?
  [str1 str2]
  (every? (fn [[l f]] (<= f (get (frequencies str1) l 0))) (frequencies str2)))

(defn scrambleHandler  [req]
  (let [str1 (get-in req [:route-params :str1]) str2 (get-in req [:route-params :str2])]
    {:status 200
     :body   (json/write-str (scramble? str1 str2))
     :header {}}))