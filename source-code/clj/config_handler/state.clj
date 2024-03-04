
(ns config-handler.state)

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @atom (map)
; {:my-config (*)}
;
; @usage
; (deref IMPORTED-CONFIG-FILES)
; =>
; {:my-config {:my-key "My value"}}
(def IMPORTED-CONFIG-FILES (atom {}))
