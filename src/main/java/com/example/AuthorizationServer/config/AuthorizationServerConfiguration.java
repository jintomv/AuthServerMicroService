package com.example.AuthorizationServer.config;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.example.AuthorizationServer.endpoints.SubjectAttributeUserTokenConverter;

@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{

	
	AuthenticationManager authenticationManager;
	KeyPair keyPair;
	boolean jwtEnabled;
	
//	@Autowired
//	PasswordEncoder encoder;
//Config Class haloooo

	@Autowired
	BCryptPasswordEncoder encoder;
	
	public AuthorizationServerConfiguration(
			AuthenticationConfiguration authenticationConfiguration,
			KeyPair keyPair,
			@Value("${security.oauth2.authorizationserver.jwt.enabled:true}") boolean jwtEnabled) throws Exception {

		this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
		this.keyPair = keyPair;
		this.jwtEnabled = jwtEnabled;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients)
			throws Exception {
		// @formatter:off
		clients.inMemory()
			.withClient("reader")
				.authorizedGrantTypes("password")
				.secret(encoder.encode("secret"))
				.scopes("message:read")
				.accessTokenValiditySeconds(600_000_000)
				.and()
			.withClient("writer")
				.authorizedGrantTypes("password")
				//.secret("{noop}secret")
				.secret(encoder.encode("secret"))
				.scopes("message:write")
				.accessTokenValiditySeconds(600_000_000)
				.and()
			.withClient("noscopes")
				.authorizedGrantTypes("password")
				.secret(encoder.encode("secret"))
				.scopes("none")
				.accessTokenValiditySeconds(600_000_000);
		// @formatter:on
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		// @formatter:off
		endpoints
			.authenticationManager(this.authenticationManager)
			.tokenStore(tokenStore());

		if (this.jwtEnabled) {
			endpoints
				.accessTokenConverter(accessTokenConverter());
		}
		// @formatter:on
	}

	@Bean
	public TokenStore tokenStore() {
		if (this.jwtEnabled) {
			return new JwtTokenStore(accessTokenConverter());
		} else {
			return new InMemoryTokenStore();
		}
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setKeyPair(this.keyPair);

		DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
		accessTokenConverter.setUserTokenConverter(new SubjectAttributeUserTokenConverter());
		converter.setAccessTokenConverter(accessTokenConverter);

		return converter;
	}
}
