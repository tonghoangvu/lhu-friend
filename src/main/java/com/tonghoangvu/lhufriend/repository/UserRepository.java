package com.tonghoangvu.lhufriend.repository;

import com.tonghoangvu.lhufriend.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
