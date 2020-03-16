(ns cljs-dapps-next.core
  (:require [integrant.core :as ig]
            [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [cljs-dapps-next.events :as events]
            [cljs-dapps-next.routes :as routes]
            [cljs-dapps-next.views :as views]
            [cljs-dapps-next.config :as config]
            [cljs-dapps-next.module.web3])
  (:require-macros [cljs-dapps-next.util :refer [read-config]]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defonce system (atom nil))

(defn start []
  (reset! system (ig/init (read-config "resources/config.edn")))
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn stop []
  (ig/halt! @system)
  (reset! system nil))

(defn ^:dev/after-load reset []
  (stop)
  (start))

(defn init []
  (dev-setup)
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (start))
