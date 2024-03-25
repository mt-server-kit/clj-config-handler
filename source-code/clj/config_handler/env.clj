
(ns config-handler.env
    (:require [io.api :as io]
              [config-handler.utils :as utils]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn read-config
  ; @ignore
  ;
  ; @param (keyword) config-id
  ;
  ; @return (*)
  [config-id]
  (or (->> config-id utils/config-id->filepath io/read-edn-file)
      (->> config-id (str "Config file not found or empty: ") Exception. throw)))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn get-config
  ; @description
  ; - Returns the content of a specific EDN config file optionally filtered to a nested value.
  ; - Throws an error if the configuration file is not found or empty.
  ;
  ; @param (keyword) config-id
  ; @param (list of *)(opt) keys
  ;
  ; @usage
  ; ;; ':my-directory/my-config' -> 'environment/my-directory/my-config.edn'
  ; (get-config :my-directory/my-config)
  ; =>
  ; {:my-key "My value"}
  ;
  ; @return (*)
  [config-id & keys]
  (if keys (-> config-id read-config (get-in keys))
           (-> config-id read-config)))
