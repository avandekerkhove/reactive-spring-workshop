package com.adeo.devmeetup.scs.demo.reactive.server.houseprices;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class HousePriceHandler {

    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<HousePrice> housePrices = 
                Flux.just(
                        new HousePrice("Paris", 9000),
                        new HousePrice("London", 13000),
                        new HousePrice("Rio", 11000));
        return ok()
                .contentType(APPLICATION_JSON)
                .body(housePrices, HousePrice.class);
    }
    
}
