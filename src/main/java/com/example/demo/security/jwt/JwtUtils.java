package com.example.demo.security.jwt;

import com.example.demo.entity.dao.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Autowired
    private UserRepository userRepository;

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpirationMs}")
    private int jwtExpirationMs;


    @Value("${ExpirationMs}")
    private int ExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findByUsername(userPrincipal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userPrincipal.getUsername()));
        return Jwts.builder()
                .setSubject("BE_Chat")
                .claim("username", user.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + ((jwtExpirationMs))))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateNewJwtToken(String userName) {
        return Jwts.builder()
                .setSubject("BE_Chat")
                .claim("username", userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + ((jwtExpirationMs))))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
