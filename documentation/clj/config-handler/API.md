
# config-handler.api Clojure namespace

##### [README](../../../README.md) > [DOCUMENTATION](../../COVER.md) > config-handler.api

### Index

- [create-config-file!](#create-config-file)

- [get-config-content](#get-config-content)

- [get-config-item](#get-config-item)

- [import-config-file!](#import-config-file)

### create-config-file!

```
@description
Creates and EDN config file on the given filepath (only if it does not exist),
and when creating, writes the body and/or the header into the created file.
The return value can be evaluted as boolean and indicates whether the creating
and writing went well.
```

```
@param (keyword) config-id
@param (map) options
{:body (*)(opt)
 :filepath (string)
 :header (string)(opt)}
```

```
@usage
(create-config-file! :my-config {:body     {...}
                                 :filepath "my-config.edn"})
```

```
@usage
(create-config-file! "my-config.edn" {:body     {...}
                                      :header   "My header\n..."
                                      :filepath "my-config.edn"})
```

```
@return (boolean)
```

<details>
<summary>Source code</summary>

```
(defn create-config-file!
  [config-id {:keys [body filepath header]}]
  (if-not (io/file-exists? filepath)
          (and (if body   (io/write-edn-file!   filepath body   {:create? true}) :no-body-passed)
               (if header (io/write-edn-header! filepath header {:create? true}) :no-header-passed))
          (return :config-file-already-exists)))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [config-handler.api :refer [create-config-file!]]))

(config-handler.api/create-config-file! ...)
(create-config-file!                    ...)
```

</details>

---

### get-config-content

```
@warning
Before using this function, the config file must be imported with the same
config ID and by using the 'import-config-file!' function.
```

```
@description
Returns the content of an imported config file.
```

```
@param (keyword) config-id
```

```
@usage
(get-config-item :my-config)
```

```
@return (*)
```

<details>
<summary>Source code</summary>

```
(defn get-config-content
  [config-id]
  (or (get state/IMPORTED-CONFIG-FILES config-id)
      (throw (Exception. (str "No config file imported with the given ID:" config-id)))))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [config-handler.api :refer [get-config-content]]))

(config-handler.api/get-config-content ...)
(get-config-content                    ...)
```

</details>

---

### get-config-item

```
@warning
Before using this function, the config file must be imported with the same
config ID and by using the 'import-config-file!' function.
```

```
@description
Returns a value read by the given item-key from an imported config file.
```

```
@param (keyword) config-id
@param (*) item-key
```

```
@usage
(get-config-item :my-config :my-key)
```

```
@return (*)
```

<details>
<summary>Source code</summary>

```
(defn get-config-item
  [config-id item-key]
  (if-let [config (get-config-content config-id)]
          (get config item-key)))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [config-handler.api :refer [get-config-item]]))

(config-handler.api/get-config-item ...)
(get-config-item                    ...)
```

</details>

---

### import-config-file!

```
@description
Reads the EDN file stored on the given filepath and stores its content in
the config handler state identified by the given config ID, that you can
use for read the imported config by using the 'get-config-item' function.
```

```
@param (keyword) config-id
@param (map) options
{:filepath (string)}
```

```
@usage
(import-config-file! :my-config {:filepath "my-config.edn"})
```

<details>
<summary>Source code</summary>

```
(defn import-config-file!
  [config-id {:keys [filepath]}]
  (if-let [file-content (io/read-edn-file filepath)]
          (swap! state/IMPORTED-CONFIG-FILES assoc config-id file-content)))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [config-handler.api :refer [import-config-file!]]))

(config-handler.api/import-config-file! ...)
(import-config-file!                    ...)
```

</details>

---

This documentation is generated with the [clj-docs-generator](https://github.com/bithandshake/clj-docs-generator) engine.

