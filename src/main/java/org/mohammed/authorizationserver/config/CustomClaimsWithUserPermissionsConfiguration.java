package org.mohammed.authorizationserver.config;

import org.mohammed.authorizationserver.repository.UserPermissionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class CustomClaimsWithUserPermissionsConfiguration {

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer(UserPermissionRepository userPermissionRepository) {
        return (context) -> {
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                context.getClaims().claims((claims) -> {
//                    Set<String> roles = AuthorityUtils.authorityListToSet(context.getPrincipal().getAuthorities())
//                            .stream()
//                            .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
//

                    var user = context.getPrincipal();
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
