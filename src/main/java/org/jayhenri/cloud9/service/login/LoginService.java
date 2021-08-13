package org.jayhenri.cloud9.service.login;

import org.jayhenri.cloud9.model.login.Login;
import org.jayhenri.cloud9.repository.login.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * The type Customer service.
 */
@Service
public class LoginService {

    private final LoginRepository loginRepository;

    /**
     * Instantiates a new Login service.
     *
     * @param loginRepository the customer repository
     */
    @Autowired
    public LoginService(LoginRepository loginRepository) {

        this.loginRepository = loginRepository;
        // this.orderDBService = orderDBService;
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

    /**
     * Add.
     *
     * @param customer the customer
     */
    public void add(Login customer) {

        loginRepository.save(customer);
    }

    /**
     * Delete.
     *
     * @param customer the customer
     */
    public void delete(Login customer) {

        loginRepository.delete(customer);
    }

    /**
     * Update.
     *
     * @param customer the customer
     */
    public void update(Login customer) {

        loginRepository.save(customer);
    }

    /**
     * Find all customers list.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the list
     */
    public List<Login> findAllLogins(Integer pageNo, Integer pageSize) {
        // String sortBy
        Pageable paging = PageRequest.of(pageNo, pageSize); // Sort.by(sortBy).ascending()
        Page<Login> pagedResult = loginRepository.findAll(paging);

        if (pagedResult.hasContent()) return pagedResult.getContent();
        else return new ArrayList<>();
    }

    /**
     * Exists by email boolean.
     *
     * @return the boolean
     */
    public boolean existsById(UUID uuid) {

        return loginRepository.existsById(uuid);
    }

    /**
     * Gets by id.
     *
     * @return the by id
     */
    public Login getById(UUID uuid) {

        return loginRepository.getById(uuid);
    }
}