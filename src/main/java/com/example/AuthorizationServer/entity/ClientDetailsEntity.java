package com.example.AuthorizationServer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Client Details for db configurartion
 * @author jinto varghese
 *
 */
@Entity
@Table(name = "clientdetails")
public class ClientDetailsEntity {

	@Id
	@Column(name = "client_id")
	private String clientId;
	
	@Column(name = "resource_id")
	private String resourceId;
	
	@Column(name = "client_secret")
	private String clientSecret;
	
	@Column(name = "scope")
	private String scope;
	
	@Column(name = "authorized_grant_types")
	private String authorizedGrantTypes;
	
	@Column(name = "register_redeedirect_uri")
	private String registeredRedirectUri;
	
	@Column(name = "authorities")
	private String authorities;
	
	@Column(name = "access_token_Validity_seconds")
	private int accessTokenValiditySeconds;
	
	@Column(name = "refresh_token_validity_seconds")
	private int refreshTokenValiditySeconds;
	
	@Column(name = "additional_information")
	private String additionalInformation;
	
	public String getClientId() {
		return clientId;
	}
	public String getResourceId() {
		return resourceId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public String getScope() {
		return scope;
	}
	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}
	public String getRegisteredRedirectUri() {
		return registeredRedirectUri;
	}
	public String getAuthorities() {
		return authorities;
	}
	public int getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}
	public int getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}
	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}
	public void setRegisteredRedirectUri(String registeredRedirectUri) {
		this.registeredRedirectUri = registeredRedirectUri;
	}
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}
	public void setRefreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
}
