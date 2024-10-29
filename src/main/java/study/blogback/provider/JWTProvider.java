package study.blogback.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JWTProvider {

    private Key key;

    @Value("${spring.jwt.secret}")
    private String secretKey;

    public String create(String email) {

        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        byte[] byteSecretKey = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(byteSecretKey);

        return Jwts.builder()
                .signWith(key,SignatureAlgorithm.HS256)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .compact();
    }

    public String validate(String jwt) {
        Claims claims = null;

        byte[] byteSecretKey = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(byteSecretKey);

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build().parseClaimsJws(jwt).getBody();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return claims.getSubject();
    }

}

