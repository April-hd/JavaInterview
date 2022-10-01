package com.hut.dao;

import com.hut.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserMongoDao extends MongoRepository<User, Long> {



}
