package com.icoderman.app.dao;

import com.icoderman.app.model.Address;
import com.icoderman.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends RevisionRepository<Address, Long, Integer>, JpaRepository<Address, Long> {


}
