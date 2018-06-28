package com.adeo.devmeetup.scs.demo.reactive.server.houseprices;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class HousePriceRouter {

    private final HousePriceHandler handler;
    
    public HousePriceRouter(HousePriceHandler handler) {
        this.handler = handler;
    }

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route(RequestPredicates.GET("/houseprices"), handler::getAllInMongo);
    }
    
}
