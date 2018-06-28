package com.adeo.devmeetup.scs.demo.reactive.client.houseprices;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@Controller
public class HousePricesController {
    
    private final WebClient webClient;
    
    public HousePricesController() {
        this.webClient = WebClient.create("http://localhost:8080");
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping(value="/ssehouseprices", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<HousePrice> streamData() {
        return webClient    
                .get()
                .uri("/streamhouseprices")
                .retrieve()
                .bodyToFlux(HousePrice.class);
    }
    
}
