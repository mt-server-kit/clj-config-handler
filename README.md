
# clj-config-handler

### Overview

The <strong>clj-config-handler</strong> is a simple EDN based config file handler
for Clojure projects.

### deps.edn

```
{:deps {bithandshake/clj-config-handler {:git/url "https://github.com/bithandshake/clj-config-handler"
                                         :sha     "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}}
```

### Current version

Check out the latest commit on the [release branch](https://github.com/bithandshake/clj-config-handler/tree/release).

### Documentation

The <strong>clj-config-handler</strong> functional documentation is [available here](documentation/COVER.md).

### Changelog

You can track the changes of the <strong>clj-config-handler</strong> library [here](CHANGES.md).

### Index

- [How to create a config file?](#how-to-create-a-config-file)

- [How to import a config file?](#how-to-import-a-config-file)

- [How to get an imported config file's content?](#how-to-get-an-imported-config-files-content)

- [How to get an item from an imported config file?](#how-to-get-an-item-from-an-imported-config-file)

# Usage

### How to create a config file?

The [`config-handler.api/create-config-file!`](documentation/clj/config-handler/API.md#create-config-file)
function creates and EDN file onto the given filepath (only if it does not exist),
and when creating, writes the body and/or the header into the created file.

> In case of the file exists it doesn't change it anymore!

```
(create-config-file! :my-config {:body     {:my-item "My value"}
                                 :filepath "my-file.edn"})
```

```
(create-config-file! :my-config {:body     {:my-item "My value"}
                                 :header   "My header\n..."
                                 :filepath "my-file.edn"})
```

### How to import a config file?

The [`config-handler.api/import-config-file!`](documentation/clj/config-handler/API.md#create-config-file)
function reads the EDN file stored on the given filepath and stores its content
in the config handler state identified by the given config ID, that you can
use for read the imported config by using the 'get-config-item' function.

```
(import-config-file! :my-config {:filepath "my-file.edn"})
```

### How to get an imported config file's content?

The [`config-handler.api/get-config-content`](documentation/clj/config-handler/API.md#get-config-content)
function returns the content of an imported config file.

> Before using this function, the config file must be imported with the same
  config ID and by using the 'import-config-file!' function.

```
(get-config-content :my-config)
```

### How to get an item from an imported config file?

The [`config-handler.api/get-config-item`](documentation/clj/config-handler/API.md#get-config-item)
function returns a value read by the given item-key from an imported config file.

> Before using this function, the config file must be imported with the same
  config ID and by using the 'import-config-file!' function.

```
(get-config-item :my-config :my-key)
```
