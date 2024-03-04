
(ns config-handler.env
    (:require [config-handler.state :as state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn get-config-content
  ; @description
  ; Returns the imported content of a specific config file.
  ;
  ; @param (keyword) config-id
  ;
  ; @usage
  ; (get-config-content :my-config)
  ; =>
  ; {:my-key "My value"}
  ;
  ; @return (*)
  [config-id]
  (or (get state/IMPORTED-CONFIG-FILES config-id)
      (throw (Exception. (str "No config file is imported with the given ID:" config-id)))))

(defn get-config-value
  ; @description
  ; Returns a specific value from the imported content of a specific config file.
  ;
  ; @param (keyword) config-id
  ; @param (*) value-key
  ;
  ; @usage
  ; (get-config-value :my-config :my-key)
  ; =>
  ; "My value"
  ;
  ; @return (*)
  [config-id value-key]
  (if-let [config-content (get-config-content config-id)]
          (get config-content value-key)))
