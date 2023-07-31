package newtest.config.multitenancy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;


@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) // Optional, enables method-level security
public class SecurityConfig {//extends WebSecurityConfigurerAdapter {

    // Your Keycloak properties - update accordingly
    private static final String ISSUER_URI = "https://localhost:8080";
    private static final String CLIENT_ID = "my_client";

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(getJwkSetUri()).build();
    }

    // Method to construct the JWK Set URI for Keycloak
    private String getJwkSetUri() {
        return ISSUER_URI + "/protocol/openid-connect/certs";
    }
}