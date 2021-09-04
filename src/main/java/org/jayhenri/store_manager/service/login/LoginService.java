package org.jayhenri.store_manager.service.login;

import org.jayhenri.store_manager.interfaces.service.other.LoginServiceI;
import org.jayhenri.store_manager.model.login.Login;
import org.jayhenri.store_manager.repository.login.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * The type Login service.
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

    public void add(Login login) {

        // login.setPassword(passwordEncoder.encode(login.getPassword()));
        loginRepository.save(login);
    }

    public void remove(Login login) {

        loginRepository.delete(login);
    }

    public void update(Login login) {

        loginRepository.save(login);
    }

    public List<Login> findAll() {

        return loginRepository.findAll();
    }

    public boolean existsById(UUID uuid) {

        return loginRepository.existsById(uuid);
    }

    public Login getById(UUID uuid) {

        return loginRepository.getById(uuid);
    }

    public boolean existsByPhoneNumber(String phoneNumber) {

        return loginRepository.existsByPhoneNumber(phoneNumber);
    }
}