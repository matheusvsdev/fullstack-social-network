package com.matheusdev.backendjava.repository;

import com.matheusdev.backendjava.entities.PostEntity;
import com.matheusdev.backendjava.entities.ProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<PostEntity, String> {

    List<PostEntity> findByAuthorObjectId(String objectId);

    @Query("{ 'author.objectId' :  { $in:  ?0 } }")
    List<PostEntity> findPostsByProfileFollowing(List<String> objectIds);
}
