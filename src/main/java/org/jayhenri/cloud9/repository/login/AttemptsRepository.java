package org.jayhenri.cloud9.repository.login;

import org.jayhenri.cloud9.model.login.Attempts;
import org.jayhenri.cloud9.model.login.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * The interface Inventory repository.
 */
@Repository
@Transactional
public interface AttemptsRepository extends JpaRepository<Attempts, UUID> {


}