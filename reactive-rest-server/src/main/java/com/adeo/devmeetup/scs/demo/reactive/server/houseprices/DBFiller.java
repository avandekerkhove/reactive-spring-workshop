package com.adeo.devmeetup.scs.demo.reactive.server.houseprices;

import java.time.Duration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

/**
 * Class implementing {@link CommandLineRunner} to insert data in repository on startup.
 */
@Component
public class DBFiller implements CommandLineRunner {

    private final HousePriceRepository repository;
    
    public DBFiller(HousePriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        repository
            .deleteAll()
            .block(Duration.ofSeconds(2)); // wait after completion with a timeout of 2 seconds
        
        Flux<HousePrice> housePrices = 
                Flux.just(
                        new HousePrice("Paris", 9000),
                        new HousePrice("London", 13000),
                        new HousePrice("Rio", 12000));
        
        repository
            .insert(housePrices)
            .blockLast(); // the method 'run' is blocking so we wait for completion
    }

}
