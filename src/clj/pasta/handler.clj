(ns pasta.handler
  (:use compojure.core
        [korma db core])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [noir.util.middleware :as noir]
            [noir.session :as session]
            [pasta.db :as db]
            [noir.response :as resp]
            ;;[pasta.views.main :as v-main]
            ;;[pasta.views.user :as v-user]))
            ))

(defdb korma-db db/default-conn)

(defroutes app-routes
  (GET "/" [] (resp/redirect "/html/main.html"))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> [(handler/site app-routes)]
      noir/app-handler
      noir/war-handler))