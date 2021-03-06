package com.adeo.devmeetup.scs.demo.reactive.server.houseprices;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Weblux functional router.
 * It just maps routes to handler functions.
 */
@Component
public class HousePriceRouter {

    private final HousePriceHandler handler;
    
    public HousePriceRouter(HousePriceHandler handler) {
        this.handler = handler;
    }

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route(GET("/houseprices"), handler::getAllInMongo)
                .andRoute(GET("/streamhouseprices"), handler::streamAll);
    }
    
}
