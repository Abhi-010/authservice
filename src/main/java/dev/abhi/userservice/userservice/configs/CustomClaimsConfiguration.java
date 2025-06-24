package dev.abhi.userservice.userservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class CustomClaimsConfiguration {
	@Bean
	public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
		return (context) -> {
			if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
				context.getClaims().claims((claims) -> {

					Set<String> roles = AuthorityUtils.authorityListToSet(context.getPrincipal().getAuthorities())
							.stream()
							.collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
//					claims.put("claim-1", "value-1");
//					claims.put("claim-2", "value-2");

					claims.put("roles", roles);

				});
			}
		};
	}
}