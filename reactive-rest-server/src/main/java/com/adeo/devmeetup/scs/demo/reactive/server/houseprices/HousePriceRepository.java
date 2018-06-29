package com.adeo.devmeetup.scs.demo.reactive.server.houseprices;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Reactive mongo repository for handling {@link HousePrice}
 */
public interface HousePriceRepository extends ReactiveMongoRepository<HousePrice, ObjectId> {

}
