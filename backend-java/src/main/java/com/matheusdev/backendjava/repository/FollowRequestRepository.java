package com.matheusdev.backendjava.repository;

import com.matheusdev.backendjava.entities.FollowRequest;
import com.matheusdev.backendjava.entities.ProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRequestRepository extends MongoRepository<FollowRequest, String> {

    List<FollowRequest> findByRequested(ProfileEntity requested);

    FollowRequest findByRequesterAndRequested(ProfileEntity requester, ProfileEntity requested);
}
