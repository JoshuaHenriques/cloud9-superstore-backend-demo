//package org.jayhenri.ecommerce.service;
//
//import org.jayhenri.ecommerce.model.CustomerDetails;
//import org.jayhenri.ecommerce.model.Login;
//import org.jayhenri.ecommerce.repository.LoginRepository;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class CustomerDetailsService implements UserDetailsService {
//
//    @Autowired
//    private LoginRepository loginRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Login login = loginRepository.findByEmail(username);
//        if(login == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new CustomerDetails(login);
//    }
//}
