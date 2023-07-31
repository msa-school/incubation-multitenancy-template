package newtest.config.multitenancy;

//import newtest.security.AuthenticationService;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

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
        // String tenant = AuthenticationService.getTenant(
        //     (HttpServletRequest) request
        // );

        // String token = ((HttpServletRequest)request).getHeader("Authorization");
        // if (token == null) {
        //     throw new ServletException("No Token provided");
        // }

        // Jwt jwt = jwtDecoder.decode(token);
        // String tenant = jwt.getClaimAsString("aud");
    
        String tenant = "account";

        TenantContext.setCurrentTenant(tenant);

        try {
            chain.doFilter(request, response);
        } finally {
            TenantContext.setCurrentTenant("");
        }
    }
}
