package org.mohammed.authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class CustomClaimsWithUserPermissionsConfiguration {

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
        return (context) -> {
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                context.getClaims().claims((claims) -> {
                    Map<String, List<String>> userPermissions = context.getPrincipal()
                            .getAuthorities()
                            .stream()
                            .collect(Collectors.groupingBy(
                                    grantedAuthority -> grantedAuthority.getAuthority().split("#")[0],
                                    Collectors.mapping(
                                            grantedAuthority -> grantedAuthority.getAuthority().split("#")[1],
                                            Collectors.toList()
                                    )
                            ));
                    claims.put("user_permissions", userPermissions);
                });
            }
        };
    }

}
