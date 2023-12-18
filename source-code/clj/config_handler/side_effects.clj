
(ns config-handler.side-effects
    (:require [config-handler.state :as state]
              [io.api               :as io]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn create-config-file!
  ; @description
  ; Creates and EDN config file at the given filepath (only if it does not exist),
  ; and when creating, writes the body and/or the header into the created file.
  ;
  ; The return value can be evaluted as boolean and indicates whether the creating
  ; and writing went well.
  ;
  ; @param (keyword) config-id
  ; @param (map) options
  ; {:body (*)(opt)
  ;  :filepath (string)
  ;  :header (string)(opt)}
  ;
  ; @usage
  ; (create-config-file! :my-config {:body     {...}
  ;                                  :filepath "my-config.edn"})
  ;
  ; @usage
  ; (create-config-file! "my-config.edn" {:body     {...}
  ;                                       :header   "My header\n..."
  ;                                       :filepath "my-config.edn"})
  ;
  ; @return (boolean)
  [config-id {:keys [body filepath header]}]
  (if-not (io/file-exists? filepath)
          (and (if body   (io/write-edn-file!   filepath body   {:create? true}) :no-body-passed)
               (if header (io/write-edn-header! filepath header {:create? true}) :no-header-passed))
          (-> :config-file-already-exists)))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn import-config-file!
  ; @description
  ; Reads the EDN file stored at the given filepath and stores its content in
  ; the config handler state identified by the given config ID, that you can
  ; use for read the imported config by using the 'get-config-item' function.
  ;
  ; @param (keyword) config-id
  ; @param (map) options
  ; {:filepath (string)}
  ;
  ; @usage
  ; (import-config-file! :my-config {:filepath "my-config.edn"})
  [config-id {:keys [filepath]}]
  (if-let [file-content (io/read-edn-file filepath)]
          (swap! state/IMPORTED-CONFIG-FILES assoc config-id file-content)))
