package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

// Todo: rename to registerLoginService
// extends a uuid wrapper?
@Service
public class LoginRegistrationService {

    @Autowired
    private LoginRepository loginRepository;

    public LoginRegistrationService() { }

    private boolean existsByEmail(String email) {
        return loginRepository.existsLoginEmailCustomQuery(email);
    }

    public void saveLogin(Login login, UUID uuid) throws CustomerAlreadyExistsException {
        if (existsByEmail(login.getEmail()))
            throw new CustomerAlreadyExistsException();

        login.setUuid(uuid);
        loginRepository.save(login);

        //encryptPassword(login);
    }

//    private void encryptPassword(Login login) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(login.getPassword());
//        login.setPassword(encodedPassword);
//
//        loginRepository.save(login);
//    }
}
