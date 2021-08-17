package org.jayhenri.cloud9.service.login;

import org.jayhenri.cloud9.interfaces.LoginServiceI;
import org.jayhenri.cloud9.model.login.Login;
import org.jayhenri.cloud9.repository.login.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * The type Customer service.
 */
@Service
public class LoginService implements LoginServiceI<Login, UUID> {

    private final LoginRepository loginRepository;

    /**
     * Instantiates a new Login service.
     *
     * @param loginRepository the login repository
     */
    @Autowired
    public LoginService(LoginRepository loginRepository) {

        this.loginRepository = loginRepository;
    }

    /**
     * Add.
     *
     * @param login the login
     */
    public void add(Login login) {

        loginRepository.save(login);
    }

    /**
     * Delete.
     *
     * @param login the login
     */
    public void remove(Login login) {

        loginRepository.delete(login);
    }

    /**
     * Update.
     *
     * @param login the login
     */
    public void update(Login login) {

        loginRepository.save(login);
    }

    /**
     * Find all logins list.
     *
     * @return the list
     */
    public List<Login> findAll() {

        return loginRepository.findAll();
    }

    /**
     * Exists by email boolean.
     *
     * @param uuid the uuid
     * @return the boolean
     */
    public boolean existsById(UUID uuid) {

        return loginRepository.existsById(uuid);
    }

    /**
     * Gets by id.
     *
     * @param uuid the uuid
     * @return the by id
     */
    public Login getById(UUID uuid) {

        return loginRepository.getById(uuid);
    }

    /**
     * Exists by phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    public boolean existsByPhoneNumber(String phoneNumber) {

        return loginRepository.existsByPhoneNumber(phoneNumber);
    }
}