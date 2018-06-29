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

/**
 * Handler for {@link HousePrice} objects.
 * It renders as Json arrays or streams. 
 */
@Component
public class HousePriceHandler {

    private final HousePriceRepository repository;
    
    public HousePriceHandler(HousePriceRepository repository) {
        this.repository = repository;
    }

    /**
     * Render static data
     * @param request
     * @return
     */
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
    
    /**
     * Render Json array from repository
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllInMongo(ServerRequest request) {
        Flux<HousePrice> housePrices = repository.findAll();
        return ok()
                .contentType(APPLICATION_JSON)
                .body(housePrices, HousePrice.class);
    }
    
    /**
     * Render a stream of {@link HousePrice}.
     * For this we use media type {@link MediaType#APPLICATION_STREAM_JSON}
     * @param request
     * @return
     */
    public Mono<ServerResponse> streamAll(ServerRequest request) {
        // Use Flux.internal to create a timer trigger every second
        Flux<HousePrice> infinite = 
                Flux
                    .interval(Duration.ZERO, Duration.ofSeconds(1))
                    .flatMap(l -> repository.findAll());
        return ok()
                .contentType(APPLICATION_STREAM_JSON)
                .body(infinite, HousePrice.class);
    }
    
}
