package com.addiis.core.gestionlogistica.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.addiis.core.gestionlogistica.utils.enums.Role;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.addiis.core.gestionlogistica.config.Constants.*;


@Configuration
public class JWTAuthtenticationConfig {

    public String getJWTToken(String username, Role role) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(role.name());

        String token = Jwts
                .builder()
                .setId("addiisJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSigningKey(SUPER_SECRET_KEY),  SignatureAlgorithm.HS512).compact();

        return TOKEN_BEARER_PREFIX + token;
    }

}