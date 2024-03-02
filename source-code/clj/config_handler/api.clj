
(ns config-handler.api
    (:require [config-handler.env :as env]
              [config-handler.side-effects :as side-effects]
              [config-handler.state :as state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (config-handler.env/*)
(def get-config-content env/get-config-content)
(def get-config-value   env/get-config-value)

; @redirect (config-handler.side-effects/*)
(def import-config-file! side-effects/import-config-file!)
(def create-config-file! side-effects/create-config-file!)

; @redirect (config-handler.state/*)
(def IMPORTED-CONFIG-FILES state/IMPORTED-CONFIG-FILES)
