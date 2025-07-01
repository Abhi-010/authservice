package dev.abhi.userservice.userservice.configs.models;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "`authorization`")
public class Authorization {
	@Id
	@Column
	private String id;
	private String registeredClientId;
	private String principalName;
	private String authorizationGrantType;
	@Column(length = 1000)
	@Lob
	private String authorizedScopes;
	@Column(length = 4000)
	@Lob
	private String attributes;
	@Column(length = 500)
	private String state;

	@Column(length = 4000)
	@Lob
	private String authorizationCodeValue;
	private Instant authorizationCodeIssuedAt;
	private Instant authorizationCodeExpiresAt;
	@Lob
	private String authorizationCodeMetadata;

	@Column(length = 4000)
	@Lob
	private String accessTokenValue;
	private Instant accessTokenIssuedAt;
	private Instant accessTokenExpiresAt;
	@Column(length = 2000)
	@Lob
	private String accessTokenMetadata;
	private String accessTokenType;
	@Column(length = 1000)
	@Lob
	private String accessTokenScopes;

	@Column(length = 4000)
	@Lob
	private String refreshTokenValue;
	private Instant refreshTokenIssuedAt;
	private Instant refreshTokenExpiresAt;
	@Column(length = 2000)
	@Lob
	private String refreshTokenMetadata;

	@Column(length = 4000)
	@Lob
	private String oidcIdTokenValue;
	private Instant oidcIdTokenIssuedAt;
	private Instant oidcIdTokenExpiresAt;
	@Column(length = 2000)
	@Lob
	private String oidcIdTokenMetadata;
	@Column(length = 2000)
	@Lob
	private String oidcIdTokenClaims;

	@Column(length = 4000)
	@Lob
	private String userCodeValue;
	private Instant userCodeIssuedAt;
	private Instant userCodeExpiresAt;
	@Column(length = 2000)
	@Lob
	private String userCodeMetadata;

	@Column(length = 4000)
	@Lob
	private String deviceCodeValue;
	private Instant deviceCodeIssuedAt;
	private Instant deviceCodeExpiresAt;
	@Column(length = 2000)
	@Lob
	private String deviceCodeMetadata;

    public void setId(String id) {
		this.id = id;
	}

    public void setRegisteredClientId(String registeredClientId) {
		this.registeredClientId = registeredClientId;
	}

    public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

    public void setAuthorizationGrantType(String authorizationGrantType) {
		this.authorizationGrantType = authorizationGrantType;
	}

    public void setAuthorizedScopes(String authorizedScopes) {
		this.authorizedScopes = authorizedScopes;
	}

    public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

    public void setState(String state) {
		this.state = state;
	}

    public void setAuthorizationCodeValue(String authorizationCode) {
		this.authorizationCodeValue = authorizationCode;
	}

    public void setAuthorizationCodeIssuedAt(Instant authorizationCodeIssuedAt) {
		this.authorizationCodeIssuedAt = authorizationCodeIssuedAt;
	}

    public void setAuthorizationCodeExpiresAt(Instant authorizationCodeExpiresAt) {
		this.authorizationCodeExpiresAt = authorizationCodeExpiresAt;
	}

    public void setAuthorizationCodeMetadata(String authorizationCodeMetadata) {
		this.authorizationCodeMetadata = authorizationCodeMetadata;
	}

    public void setAccessTokenValue(String accessToken) {
		this.accessTokenValue = accessToken;
	}

    public void setAccessTokenIssuedAt(Instant accessTokenIssuedAt) {
		this.accessTokenIssuedAt = accessTokenIssuedAt;
	}

    public void setAccessTokenExpiresAt(Instant accessTokenExpiresAt) {
		this.accessTokenExpiresAt = accessTokenExpiresAt;
	}

    public void setAccessTokenMetadata(String accessTokenMetadata) {
		this.accessTokenMetadata = accessTokenMetadata;
	}

    public void setAccessTokenType(String accessTokenType) {
		this.accessTokenType = accessTokenType;
	}

    public void setAccessTokenScopes(String accessTokenScopes) {
		this.accessTokenScopes = accessTokenScopes;
	}

    public void setRefreshTokenValue(String refreshToken) {
		this.refreshTokenValue = refreshToken;
	}

    public void setRefreshTokenIssuedAt(Instant refreshTokenIssuedAt) {
		this.refreshTokenIssuedAt = refreshTokenIssuedAt;
	}

    public void setRefreshTokenExpiresAt(Instant refreshTokenExpiresAt) {
		this.refreshTokenExpiresAt = refreshTokenExpiresAt;
	}

    public void setRefreshTokenMetadata(String refreshTokenMetadata) {
		this.refreshTokenMetadata = refreshTokenMetadata;
	}

    public void setOidcIdTokenValue(String idToken) {
		this.oidcIdTokenValue = idToken;
	}

    public void setOidcIdTokenIssuedAt(Instant idTokenIssuedAt) {
		this.oidcIdTokenIssuedAt = idTokenIssuedAt;
	}

    public void setOidcIdTokenExpiresAt(Instant idTokenExpiresAt) {
		this.oidcIdTokenExpiresAt = idTokenExpiresAt;
	}

    public void setOidcIdTokenMetadata(String idTokenMetadata) {
		this.oidcIdTokenMetadata = idTokenMetadata;
	}

    public void setOidcIdTokenClaims(String idTokenClaims) {
		this.oidcIdTokenClaims = idTokenClaims;
	}

    public void setUserCodeValue(String userCodeValue) {
		this.userCodeValue = userCodeValue;
	}

    public void setUserCodeIssuedAt(Instant userCodeIssuedAt) {
		this.userCodeIssuedAt = userCodeIssuedAt;
	}

    public void setUserCodeExpiresAt(Instant userCodeExpiresAt) {
		this.userCodeExpiresAt = userCodeExpiresAt;
	}

    public void setUserCodeMetadata(String userCodeMetadata) {
		this.userCodeMetadata = userCodeMetadata;
	}

    public void setDeviceCodeValue(String deviceCodeValue) {
		this.deviceCodeValue = deviceCodeValue;
	}

    public void setDeviceCodeIssuedAt(Instant deviceCodeIssuedAt) {
		this.deviceCodeIssuedAt = deviceCodeIssuedAt;
	}

    public void setDeviceCodeExpiresAt(Instant deviceCodeExpiresAt) {
		this.deviceCodeExpiresAt = deviceCodeExpiresAt;
	}

    public void setDeviceCodeMetadata(String deviceCodeMetadata) {
		this.deviceCodeMetadata = deviceCodeMetadata;
	}
}