package org.jayhenri.ecommerce.configuration;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http 
        .httpBasic()
    .and()
        .authorizeRequests()
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated();
    }
}