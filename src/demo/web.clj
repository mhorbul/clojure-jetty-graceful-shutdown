(ns demo.web
  (:use ring.adapter.jetty
        ring.middleware.params)
  (:require [clojure.tools.logging :as log]))

(defn handler [{params :params}]
  (. Thread (sleep 9000))
  {:status  200
   :headers {"Content-Type" "text/plain"}
   :body    (str "Hello " (params "name") "\n")})

(def app
  (wrap-params handler))

(defn graceful-restart [jetty]
    (.setGracefulShutdown jetty 10000)
    (.setStopAtShutdown jetty true))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (run-jetty app {:port port, :configurator graceful-restart})))
