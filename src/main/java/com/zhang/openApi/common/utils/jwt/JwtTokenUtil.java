package com.zhang.openApi.common.utils.jwt;

import com.zhang.openApi.common.config.security.JwtUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;
    private Clock clock = DefaultClock.INSTANCE;

    private static final String CLAIM_KEY_CLIENT_ID = "clientId";
    private static final String CLAIM_KEY_CLIENT_NAME = "clientName";
    private static final String CLAIM_KEY_ROLES = "roles";
    private static final String CLAIM_KEY_AUTHORITIES = "scope";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String getClientNameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public JwtUserDetails getUserFromToken(String token) {
        JwtUserDetails user;
        try {
            final Claims claims = getClaimsFromToken(token);
            String clientId = getClientIdFromToken(token);
            String clientName = claims.getSubject();
            List auths = (List)claims.get(CLAIM_KEY_AUTHORITIES);
            Collection<? extends GrantedAuthority> authorities = parseArrayToAuthorities(auths);
            ArrayList roles = claims.get(CLAIM_KEY_ROLES, ArrayList.class);
            user = new JwtUserDetails(clientId, clientName, authorities, roles);
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    public String getClientIdFromToken(String token) {
        String clientId;
        try {
            final Claims claims = getClaimsFromToken(token);
            clientId = (String)claims.get(CLAIM_KEY_CLIENT_ID);
        } catch (Exception e) {
            clientId = "noClientId";
        }
        return clientId;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody();
    }

    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(clock.now());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }


    public String generateToken(UserDetails userDetails) {
        JwtUserDetails jwtUserDetails = (JwtUserDetails) userDetails;
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_CLIENT_ID, jwtUserDetails.getClientId());
        claims.put(CLAIM_KEY_CLIENT_NAME, userDetails.getUsername());
        return doGenerateToken(claims, userDetails.getUsername());
    }


    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(createdDate)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUserDetails user = (JwtUserDetails) userDetails;
        final String clientName = getClientNameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);
        return (
                clientName.equals(user.getUsername())
                && !isTokenExpired(token)
                && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate())
        );
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

    private Collection<? extends GrantedAuthority> parseArrayToAuthorities(List roles) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority;
        for (Object role : roles) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap)role;
            authority = new SimpleGrantedAuthority(linkedHashMap.get("authority"));
            authorities.add(authority);
        }
        return authorities;
    }
}
