
(ns config-handler.utils)

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn config-id->filepath
  ; @ignore
  ;
  ; @param (keyword) config-id
  ;
  ; @usage
  ; (config-id->filepath :my-directory/my-config)
  ; =>
  ; "environment/my-directory/my-config.edn"
  ;
  ; @return (string)
  [config-id]
  (if-let [directory (namespace config-id)]
          (str "environment/" directory "/" (name config-id) ".edn")
          (str "environment/"               (name config-id) ".edn")))
