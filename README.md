Intorduction
--
This [Demo Video](http://youtu.be/PbWRo4JPfIU) shows how jetty is restarted gracefuly.
The old request is being served even when JVM process is shutdown. And the new one started and is listening on the same port.

How To Run It
--
In console #1:

   git clone https://github.com/mhorbul/clojure-jetty-graceful-shutdown.git
   bundle install
   foreman start

In console #2:

   https://github.com/mhorbul/clojure-jetty-graceful-shutdown.git

Then shutdown process in the console #1 by CTRL+C. Make sure request in console #2 is completed successfully.
