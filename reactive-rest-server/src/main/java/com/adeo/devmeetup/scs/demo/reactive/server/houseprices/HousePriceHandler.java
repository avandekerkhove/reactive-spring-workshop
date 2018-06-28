package com.adeo.devmeetup.scs.demo.reactive.server.houseprices;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class HousePriceHandler {

    private final HousePriceRepository repository;
    
    public HousePriceHandler(HousePriceRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> getAllStatic(ServerRequest request) {
        Flux<HousePrice> housePrices = 
                Flux.just(
                        new HousePrice("Paris", 9000),
                        new HousePrice("London", 13000),
                        new HousePrice("Rio", 11000));
        return ok()
                .contentType(APPLICATION_JSON)
                .body(housePrices, HousePrice.class);
    }
    
    public Mono<ServerResponse> getAllInMongo(ServerRequest request) {
        Flux<HousePrice> housePrices = repository.findAll();
        return ok()
                .contentType(APPLICATION_JSON)
                .body(housePrices, HousePrice.class);
    }
    
    public Mono<ServerResponse> streamAll(ServerRequest request) {
        Flux<HousePrice> infinite = 
                Flux
                    .interval(Duration.ZERO, Duration.ofSeconds(1))
                    .flatMap(l -> repository.findAll());
        return ok()
                .contentType(APPLICATION_STREAM_JSON)
                .body(infinite, HousePrice.class);
    }
    
    
}
