package newtest.config.multitenancy;

import javax.crypto.SecretKey;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenParser {


    public String getTenantId(String token){
    
     // Jwt jwt = jwtDecoder.decode(token);
        // String tenant = jwt.getClaimAsString("aud");
    
        SecretKey key = Keys.hmacShaKeyFor("HKFKYP7kb8OMldAgfvnk27FhRPOv8Y7H".getBytes());
 
        String tenant = Jwts
        .parserBuilder()
        .setSigningKey(key)
        .requireIssuer("http://localhost:8080")
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getAudience();

        return tenant;

    }

}
