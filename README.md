# reactive-spring-workshop
Workshop with quick pres and live coding about reactive spring (webflux, reactor, reactive mongoDb..)

Slides are available at https://avandekerkhove.github.io/reactive-spring-workshop/ (thanks to reveal.js framework - https://revealjs.com)

There are 2 spring webflux applications : reactive-rest-server and reactive-rest-client.

## reactive-rest-server
This application exposes endpoints to get data in mongo repository.
* 1st endpoint `curl -i http://localhost:8080/houseprices` gets data in common way (render a Json array)
* 2nd endpoint uses media type *application/stream+json* to render data as a stream : `curl -i http://localhost:8080/streamhouseprices`

## reactive-rest-client
This applications calls the rest server thanks to WebClient (reactive HTTP client alternative to RestTemplate).
We use media type *text/event-stream* to render data as *server-sent events*, and we display data on browser with automatic refresh, without making new requests to server. Test it in http://localhost:8081/
