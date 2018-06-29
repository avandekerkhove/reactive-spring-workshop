package com.adeo.devmeetup.scs.demo.reactive.client.houseprices;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

/**
 * Annotation style controller
 */
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
    
    /**
     * Use {@link WebClient} to call streaming service.
     * We render with media type "text/event-stream" to trigger server-sent events.
     * @return
     */
    @GetMapping(value="/ssehouseprices", produces=TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<HousePrice> streamData() {
        return webClient    
                .get()
                .uri("/streamhouseprices")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(HousePrice.class);
    }
    
}
