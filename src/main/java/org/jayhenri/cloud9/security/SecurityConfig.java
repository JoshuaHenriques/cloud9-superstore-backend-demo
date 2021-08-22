//package org.jayhenri.cloud9.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
////
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final UserDetailsServiceImpl userDetailsService;
//
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//
//
//    @Autowired
//    public SecurityConfig(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userDetailsService = userDetailsService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManagerBean());
////        authenticationFilter.setFilterProcessesUrl("/api/login");
//
////        http
////                .authorizeRequests().anyRequest().hasAnyAuthority("user", "admin")
////                .and()
////                .authorizeRequests().antMatchers("/login**").permitAll()
////                .and()
////                .formLogin().loginPage("/login").permitAll()
////                .and()
////                .logout().logoutSuccessUrl("/login").permitAll()
////                .and()
////                .csrf().disable();
////        http.sessionManagement().sessionCreationPolicy(STATELESS);
////        http.authorizeRequests().anyRequest().authenticated();
//        //http.addFilter(authenticationFilter);
//    }
////
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
//}
