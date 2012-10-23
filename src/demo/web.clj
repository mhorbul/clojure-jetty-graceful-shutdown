(ns demo.web
  (:use ring.adapter.jetty
        ring.middleware.params)
  (:require [clojure.tools.logging :as log]))

(def jetty (atom nil))

(defn handler [{params :params}]
  (. Thread (sleep 50000))
  {:status  200
   :headers {"Content-Type" "text/plain"}
   :body    (str "Hello " (params "name") "\n")})

(def app
  (wrap-params handler))

(defn shutdown []
 (log/info "Initiating shutdown...")
 (.setShutdown @jetty true))

(defn graceful-restart [jetty]
    (.setGracefulShutdown jetty 55000)
    (.setStopAtShutdown jetty true))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (.addShutdownHook (Runtime/getRuntime) (Thread. shutdown))
    (reset! jetty (run-jetty app {:port port, :configurator graceful-restart}))))
