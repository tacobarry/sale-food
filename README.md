# sale-food

For run this project you need access the folder backend and run:

> `mvn spring-boot:run -Drun.arguments="--server.port=8080"`
----
This port 8080 it's very important for run the proxy that was created in frontend of this project.

If you change this port it's only open the file `proxy.conf.js` in the root of the project: `https://github.com/tacobarry/sale-food-front.git`; and change the line `"target": "http://localhost:8080"` putting the correct port before to start it.