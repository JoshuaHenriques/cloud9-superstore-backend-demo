package org.jayhenri.store_manager.security;
//package org.jayhenri.cloud9.security;
//
//import org.jayhenri.cloud9.model.login.Login;
//import org.jayhenri.cloud9.repository.login.LoginRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//class UserDetailsServiceImpl implements UserDetailsService {
//
//    LoginRepository loginRepository;
//
//    @Autowired
//    public UserDetailsServiceImpl(LoginRepository loginRepository) {
//
//        this.loginRepository = loginRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Login login = loginRepository.getByEmail(username);
//
//        if (login.equals(null))
//            throw new UsernameNotFoundException("EMAIL OR PASSWORD DOESN'T MATCH");
//
//        return login;
//    }
//}
