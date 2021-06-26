package org.jayhenri.ecommerce.repository;

import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginRepository extends JpaRepository<Login, UUID> {

    @Query("select case when count(c)> 0 then true else false end from Login c where lower(c.email) like lower(:email)")
    boolean existsLoginEmailCustomQuery(@Param("email") String email);

    @Query("SELECT l FROM Login l WHERE l.email = ?1")
    public Login findByEmail(String email);
}