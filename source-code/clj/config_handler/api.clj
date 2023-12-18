
(ns config-handler.api
    (:require [config-handler.env          :as env]
              [config-handler.side-effects :as side-effects]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (config-handler.env/*)
(def get-config-content env/get-config-content)
(def get-config-item    env/get-config-item)

; @redirect (config-handler.side-effects/*)
(def create-config-file! side-effects/create-config-file!)
(def import-config-file! side-effects/import-config-file!)
