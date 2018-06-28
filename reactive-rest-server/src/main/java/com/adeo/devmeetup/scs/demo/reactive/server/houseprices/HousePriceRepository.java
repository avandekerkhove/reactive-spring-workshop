package com.adeo.devmeetup.scs.demo.reactive.server.houseprices;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface HousePriceRepository extends ReactiveMongoRepository<HousePrice, ObjectId> {

}
