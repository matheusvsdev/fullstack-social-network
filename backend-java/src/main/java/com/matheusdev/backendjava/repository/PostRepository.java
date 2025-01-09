package com.matheusdev.backendjava.repository;

import com.matheusdev.backendjava.entities.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<PostEntity, String> {

    List<PostEntity> findByAuthorObjectId(String objectId);
}
