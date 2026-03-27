package com.cv.repository;

import com.cv.model.CvInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CvRepository extends MongoRepository<CvInfo, String> {
    // MongoRepository gives us standard CRUD operations for free!
}