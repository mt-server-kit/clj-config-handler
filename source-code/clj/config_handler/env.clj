
(ns config-handler.env
    (:require [config-handler.state :as state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn get-config-content
  ; @warning
  ; Before using this function, the config file must be imported with the same
  ; config ID and by using the 'import-config-file!' function.
  ;
  ; @description
  ; Returns the content of an imported config file.
  ;
  ; @param (keyword) config-id
  ;
  ; @usage
  ; (get-config-item :my-config)
  ;
  ; @return (*)
  [config-id]
  (or (get state/IMPORTED-CONFIG-FILES config-id)
      (throw (Exception. (str "No config file imported with the given ID:" config-id)))))

(defn get-config-item
  ; @warning
  ; Before using this function, the config file must be imported with the same
  ; config ID and by using the 'import-config-file!' function.
  ;
  ; @description
  ; Returns a value read by the given item-key from an imported config file.
  ;
  ; @param (keyword) config-id
  ; @param (*) item-key
  ;
  ; @usage
  ; (get-config-item :my-config :my-key)
  ;
  ; @return (*)
  [config-id item-key]
  (if-let [config (get-config-content config-id)]
          (get config item-key)))
