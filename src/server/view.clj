(ns server.view
   (:require [hiccup.core :refer [html]]))

(defn notFoundComponent
  []
  (html [:div {:style "color:red; margin:80px auto; width:250px; box-shadow:2px 2px 2px 2px #dceafa; padding:20px"}
   [:h1 {:style "color: red"} "Page not found."]]))