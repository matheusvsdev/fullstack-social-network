package com.matheusdev.backendjava.repository;

import com.matheusdev.backendjava.entities.RoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<RoleEntity, String> {

    RoleEntity findByAuthority(String authority);
}
