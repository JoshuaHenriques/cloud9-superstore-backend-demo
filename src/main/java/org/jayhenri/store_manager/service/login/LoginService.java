package org.jayhenri.store_manager.service.login;

import org.jayhenri.store_manager.interfaces.service.other.LoginServiceI;
import org.jayhenri.store_manager.model.login.Login;
import org.jayhenri.store_manager.repository.login.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * The type Customer service.
 */
@Service
public class LoginService implements LoginServiceI {

    private final LoginRepository loginRepository;

    // private final PasswordEncoder passwordEncoder;

    /**
     * Instantiates a new Login service.
     *
     * @param loginRepository the login repository
     */
    @Autowired
    public LoginService(LoginRepository loginRepository) {

        this.loginRepository = loginRepository;
        // this.passwordEncoder = passwordEncoder;
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        if (!loginRepository.existsByEmail(email))
//            throw new UsernameNotFoundException("Email not found");
//
//        Login login = loginRepository.getByEmail(email);
//
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        login.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
//
//        return new org.springframework.security.core.userdetails.User(login.getEmail(), login.getPassword(), authorities);
//    }

    /**
     * Add.
     *
     * @param login the login
     */
    public void add(Login login) {

        // login.setPassword(passwordEncoder.encode(login.getPassword()));
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