package dev.abhi.userservice.userservice.utils;

import dev.abhi.userservice.userservice.models.SessionStatus;
import dev.abhi.userservice.userservice.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final MacAlgorithm alg = Jwts.SIG.HS256;
    // Use a fixed key instead of generating it every time
    private static final SecretKey key1 = alg.key().build();
    private static final long EXPIRATION_MS = 20000; // 1 hour

    public static String generateToken(User user) {
        // Create a test key suitable for the desired HMAC-SHA algorithm:

//        MacAlgorithm alg = Jwts.SIG.HS256; //or HS384 or HS256
//        SecretKey key = alg.key().build();

        Map<String, Object> jsonForJwt = new HashMap<>();
        jsonForJwt.put("email", user.getEmail());
        jsonForJwt.put("roles", user.getRoles());
        jsonForJwt.put("user_id", user.getId());
        jsonForJwt.put("creationDate", new Date());
//        jsonForJwt.put("expireAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));
        jsonForJwt.put("expireAt", new Date(System.currentTimeMillis() + EXPIRATION_MS));

//        byte[] content = message.getBytes(StandardCharsets.UTF_8);
//              Create the compact JWS:
        return Jwts.builder()
                .claims(jsonForJwt)
//               .content(content, "text/plain")
                .signWith(key1,alg)
                .compact();
    }

    public static SessionStatus validateTokenLocally(String jwsToken,Long userId){

        try{
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(key1)
                    .build()
                    .parseSignedClaims(jwsToken);
        }
        catch (Exception e) {
            System.out.println("Catch in catch block...******");
            System.err.println("Invalid JWT: " + e.getMessage());
            return SessionStatus.ENDED;
        }
        return SessionStatus.ACTIVE;
    }
}
