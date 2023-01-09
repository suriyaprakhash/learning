//package com.suriya.mutualsslserver;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.SecurityFilterChain;
//
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .x509()
//                .subjectPrincipalRegex("CN=(.*?)(?:,|$)")
//                .userDetailsService(userDetailsService());
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                if (username.equals("Bob")) {
//                    return new User(username, "",
//                            AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
//                }
//                throw new UsernameNotFoundException("User not found!");
//            }
//        };
//    }
//}
