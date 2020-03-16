(ns cljs-dapps-next.util
  (:require [integrant.core :as ig]
            #?(:clj [clojure.data.json :as json])))

(defmacro read-config [file]
  #?(:clj (ig/read-string
           {:readers {'json #(-> %
                                 slurp
                                 (json/read-str :key-fn keyword))}}
           (slurp file))))
