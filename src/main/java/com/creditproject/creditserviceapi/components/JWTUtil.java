package com.creditproject.creditserviceapi.components;

import com.creditproject.creditserviceapi.domain.User;
import com.creditproject.creditserviceapi.domain.enums.Profile;
import com.creditproject.creditserviceapi.exceptions.InvalidProfileException;
import com.creditproject.creditserviceapi.exceptions.InvalidTokenException;
import com.creditproject.creditserviceapi.rest.responses.TokenResponse;
import com.creditproject.creditserviceapi.vo.UserAuth;
import com.creditproject.creditserviceapi.vo.enums.RequestEnums;
import com.creditproject.creditserviceapi.vo.enums.TokenEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JWTUtil {
  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private Long expiration;

  private final HttpServletRequest request;

  public TokenResponse getToken(final User user) {
    return TokenResponse.builder()
        .type(TokenEnum.BEARER.getValue())
        .token(generateToken(user))
        .duration(expiration.toString())
        .build();
  }

  private String generateToken(final User user) {
    return Jwts.builder()
        .setSubject(user.getEmail())
        .claim("id", user.getId())
        .claim(
            "role",
            user.getProfiles().stream()
                .findFirst()
                .map(Profile::getRole)
                .orElseThrow(InvalidProfileException::new))
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(SignatureAlgorithm.HS512, secret.getBytes())
        .compact();
  }

  public boolean isValid(final String token) {
    final var claims = getClaims(token);
    if (claims == null) {
      return Boolean.FALSE;
    }
    return Objects.equals(claims.getSubject(), getUsername(token));
  }

  private Claims getClaims(final String token) {
    try {
      return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
    } catch (Exception ex) {
      return null;
    }
  }

  public String getUsername(final String token) {
    Claims claims = getClaims(token);
    if (claims != null) {
      return claims.getSubject();
    }
    return null;
  }

  public UserAuth getAuth() {
    final var claims = getClaims(getToken());
    if (claims == null) {
      throw new InvalidTokenException();
    }
    return UserAuth.builder()
        .email(claims.getSubject())
        .id(claims.get("id", Long.class))
        .role(claims.get("role", String.class))
        .build();
  }

  public String getToken() {
    return Optional.ofNullable(request.getHeader(RequestEnums.AUTHORIZATION_HEADER.getValue()))
        .map(t -> t.substring(7))
        .orElseThrow(InvalidTokenException::new);
  }
}
