(ns cljs-dapps-next.module.web3
  (:require [integrant.core :as ig]
            ["web3" :as Web3]))

(defmethod ig/init-key ::web3 [_ _]
  (Web3. "ws://localhost:8545"))

(defmethod ig/init-key ::contract [_ {:keys [network-id contract web3]}]
  (let [{:keys [abi networks]} contract
        network-id-key (keyword (str network-id))
        address (-> networks network-id-key :address)
        contract (new (aget web3 "eth" "Contract") (clj->js abi) address)]
    (js/console.log contract)
    contract))
