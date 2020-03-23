(ns cljs-dapps-next.events
  (:require [re-frame.core :as re-frame]
            [cljs-dapps-next.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::initialize-system
 (fn [db [_ system]]
   (assoc db :system system)))

(re-frame/reg-event-db
 ::set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))
