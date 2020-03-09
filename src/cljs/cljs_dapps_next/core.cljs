(ns cljs-dapps-next.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [cljs-dapps-next.events :as events]
   [cljs-dapps-next.routes :as routes]
   [cljs-dapps-next.views :as views]
   [cljs-dapps-next.config :as config]
   ))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
