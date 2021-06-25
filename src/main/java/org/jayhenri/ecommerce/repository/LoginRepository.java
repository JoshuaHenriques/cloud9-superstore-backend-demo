package org.jayhenri.ecommerce.repository;

import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginRepository extends JpaRepository<Login, UUID> {

//    @Query(value = "", nativeQuery = true)
//    boolean existsByPhoneNumber(@param("phonenumber") String phonenumber) {
//
//    }
}