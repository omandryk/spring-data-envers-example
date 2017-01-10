package com.icoderman.app.dao;

import com.icoderman.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends RevisionRepository<User, Long, Integer>, JpaRepository<User, Long> {

	User findByUsername(String username);

}
