package com.yeswekhan.construction_service.repository;

import com.yeswekhan.construction_service.model.Construction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConstructionRepository extends MongoRepository<Construction, String> {
}
