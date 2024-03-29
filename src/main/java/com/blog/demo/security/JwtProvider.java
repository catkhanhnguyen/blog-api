package com.blog.demo.security;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.blog.demo.model.Role;
import com.blog.demo.service.UserInfoDetailsService;
import com.blog.demo.utils.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@SuppressWarnings("deprecation")
@Component
public class JwtProvider {

    private static final String SECRET_KEY_STRING = "L5bJEqyHmDWmi8vQ0jHCEqfCt8tQsw4sUGZEqQrAFBQ=L5bJEqyHmDWmi8vQ0jHCEqfCt8tQsw4sUGZEqQrAFBQ=";

    @Lazy
    @Autowired
    private UserInfoDetailsService userInfoDetailsService;

    public String generateToken(String username, List<Role> roles, long expiration) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Utils.getExpiration(expiration, ChronoUnit.MINUTES))
            .signWith(getSignKey(), SignatureAlgorithm.HS512)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userInfoDetailsService.loadUserByUsername(extractUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
    }

    public Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY_STRING);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        JwtParser parser = Jwts.parser()
                            .setSigningKey(getSignKey());
        return parser.parseClaimsJws(token).getBody();
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
