package com.adeo.devmeetup.scs.demo.reactive.server.houseprices;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * This class updates prices in mongo DB to see changes in the streams
 */
@Component
@EnableScheduling
public class DBUpdater {

    private final HousePriceRepository repository;
    
    public DBUpdater(HousePriceRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedDelayString="PT3S")
    public void updateHousePrices() {
        repository
            .findAll()
            .doOnNext(hp -> {
                // increase by 10
                hp.setPricePerSquare(hp.getPricePerSquare()+10);
                repository.save(hp).subscribe();
            })
            .blockLast(); // subscribe and wait for completion
    }
    
}
