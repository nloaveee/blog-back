package study.blogback.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JWTProvider {

    private SecretKey secretKey;

    public JWTProvider(@Value("${spring.jwt.secret}")String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String create(String email) {

        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(expiredDate)
                .signWith(secretKey)
                .compact();
    }

    public String validate(String jwt) {
        Claims claims = null;

        try {
            claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build().parseSignedClaims(jwt).getPayload();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return claims.getSubject();
    }

}

