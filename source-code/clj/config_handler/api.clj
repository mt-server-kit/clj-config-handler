
(ns config-handler.api
    (:require [config-handler.env :as env]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (config-handler.env/*)
(def get-config env/get-config)
