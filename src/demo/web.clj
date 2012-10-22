(ns demo.web
  (:use ring.adapter.jetty))

(defn app [req]
  (. Thread (sleep 10000))
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello from Clojure!\n"})

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (run-jetty app {:port port})))

