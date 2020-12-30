(ns minimal.web
  (:require
   [clojure.pprint :refer [pprint]]
   [ring.adapter.jetty :as jetty]))

(defn http-handler [request]
  (if (= (:uri request) "/echo")
    {:status 200
     :headers {"content-type" "text/html"}
     :body (str "<html><body><p>Hi from my web server! Here's your request:</p><pre>"
                (with-out-str (pprint request))
                "</pre></body></html>")}
    {:status 404
     :headers {}
     :body "Not found"}))

(defn start-server
  ([] (start-server 3000))
  ([port]
   (jetty/run-jetty #'http-handler {:port port :join? false})))

(defn stop-server [server]
  (.stop server))

(comment

  (def server (start-server))
  ;; visit http://localhost:3000/echo
  (stop-server server)

  )
