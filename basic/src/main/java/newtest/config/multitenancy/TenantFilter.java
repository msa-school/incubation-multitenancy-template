package newtest.config.multitenancy;

//import newtest.security.AuthenticationService;
import java.io.IOException;

import javax.crypto.SecretKey;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
@Order(1)
class TenantFilter implements Filter {

    // Autowire JwtDecoder instance
    @Autowired
    private JwtDecoder jwtDecoder;


    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {

        String token = ((HttpServletRequest)request).getHeader("Authorization");
        if (token == null) {
            throw new ServletException("No Token provided");
        }

        // Jwt jwt = jwtDecoder.decode(token);
        // String tenant = jwt.getClaimAsString("aud");
    
        SecretKey key = Keys.hmacShaKeyFor("HKFKYP7kb8OMldAgfvnk27FhRPOv8Y7H".getBytes());
 
        String tenant = Jwts
        .parserBuilder()
        .setSigningKey(key)
        .requireIssuer("http://localhost:8080")
        .build()
        .parseClaimsJws(token.replace("Bearer ", ""))
        .getBody()
        .getAudience();


        // String tenant = "account";

        TenantContext.setCurrentTenant(tenant);

        try {
            chain.doFilter(request, response);
        } finally {
            TenantContext.setCurrentTenant("");
        }
    }
}
