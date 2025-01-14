package com.matheusdev.backendjava.repository;

import com.matheusdev.backendjava.entities.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<PostEntity, String> {

    @Query(value = "{ 'author.objectId' : ?0 }", sort = "{ 'createdAt' : -1 }")
    List<PostEntity> findByAuthorObjectId(String objectId);

    @Query(value = "{ 'author.objectId' : { $in: ?0 } }", sort = "{ 'createdAt' : -1 }")
    List<PostEntity> findPostsByProfileFollowing(List<String> objectIds);
}
