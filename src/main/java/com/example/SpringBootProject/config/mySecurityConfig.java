package com.example.SpringBootProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity ;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
// import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
// import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
@EnableWebSecurity
public class mySecurityConfig {
    

    // configure the http req with spring security
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        // mvcMatcher builder is needed beacuse /home is mvc @Controller endpoint not @ResController
        return (
            http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> 
                    authz
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/home/users")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/users/**")).hasAnyRole("USER","ADMIN")
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/home/admin")).hasRole("ADMIN")
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/home/admin/**")).hasRole("ADMIN")
                )
                .httpBasic(Customizer.withDefaults())
                .build() 
        ); 
    }



    // ignore certain api endpoints
    /* 
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(HandlerMappingIntrospector introspector) {
        return (web) -> web.ignoring().requestMatchers(
            AntPathRequestMatcher.antMatcher("/home")  , 
            new MvcRequestMatcher(introspector, "/home")
        );
    }
    */



    // Defining Users with roles in inMemory Db (Temporary)
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails admin = User.withUsername("himadri")
            .password(this.passwordEncoder().encode("mypass"))
            .roles("ADMIN")
            .build();

        UserDetails user = User.withUsername("user")
            .password(this.passwordEncoder().encode("userpass"))
            .roles("USER")
            .build();
        /*
         * 
         * N number of users can be configured
         * 
          */
        return new InMemoryUserDetailsManager(admin,user);
    }


    // define our password encoder
    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10) ;
    }


}
