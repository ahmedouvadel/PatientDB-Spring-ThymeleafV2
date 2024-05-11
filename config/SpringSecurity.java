package com.exmple.patientsmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //From Spring6 no need to set user details , it will automatically set user details, service and password encoded objects to auntication.
    }

    // configure SecurityFilterChain

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests() // Use authorizeRequests instead of authorizeHttpRequests (newer versions)
                .requestMatchers(new AntPathRequestMatcher("/register/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/index")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/home")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/listpatient")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/medecins")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/delete")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/formPatients")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/editPatient")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/save")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/deleteMedecin")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/medecinForm")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/saveMedecin")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/editMedecin")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/register/save")).permitAll()

                // Allow all authenticated users access to all paths
                .anyRequest().authenticated() // All other paths require authentication

                .and()
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/home")
                                .permitAll()
                )
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()

                );
        return http.build();
    }

    // ... other configurations
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());      //Just passwordEncoder bean and UserDetailsService bean must be there then no need of this configuration setting as sping6 will automatically set user details, service and password encoded objects to auntication.
    }
}