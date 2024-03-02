
(ns config-handler.side-effects
    (:require [config-handler.state :as state]
              [io.api               :as io]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn import-config-file!
  ; @description
  ; - Reads a specific EDN config file from the given filepath and stores its content in the 'IMPORTED-CONFIG-FILES' atom.
  ; - Returns the imported content of the file.
  ;
  ; @param (keyword) config-id
  ; @param (map) options
  ; {:filepath (string)}
  ;
  ; @usage
  ; (import-config-file! :my-config {:filepath "my-config.edn"})
  ; =>
  ; {:my-value "My value"}
  ;
  ; @return (*)
  [config-id {:keys [filepath]}]
  (when-let [file-content (io/read-edn-file filepath)]
            (swap! state/IMPORTED-CONFIG-FILES assoc config-id file-content)
            (-> file-content)))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn create-config-file!
  ; @description
  ; - Creates an EDN config file at the given filepath (in case it does not exist).
  ; - Imports the created (or existed) file and stores its content in the 'IMPORTED-CONFIG-FILES' atom.
  ; - Returns the imported content of the file.
  ;
  ; @param (keyword) config-id
  ; @param (map) options
  ; {:body (*)(opt)
  ;  :filepath (string)
  ;  :header (string)(opt)}
  ;
  ; @usage
  ; (create-config-file! :my-config {:body     {:my-value "My value"}
  ;                                  :filepath "my-config.edn"})
  ; =>
  ; {:my-value "My value"}
  ;
  ; @usage
  ; (create-config-file! :my-config {:body     {:my-value "My value"}
  ;                                  :header   "My header"
  ;                                  :filepath "my-config.edn"})
  ; =>
  ; {:my-value "My value"}
  ;
  ; @return (boolean)
  [config-id {:keys [body filepath header] :as options}]
  (when-not (io/file-exists? filepath)
            (if body   (io/write-edn-file!   filepath body   {:create? true}))
            (if header (io/write-edn-header! filepath header {:create? true})))
  (import-config-file! config-id options))
