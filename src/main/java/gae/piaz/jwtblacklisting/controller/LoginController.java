package gae.piaz.jwtblacklisting.controller;


import gae.piaz.jwtblacklisting.controller.dto.LoginDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Value("${jwt.key}")
    private String jwtKey;

    @Value("${jwt.duration:0}")
    private int tokenDuration;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO request) {

        if (!request.getUsername().equals("username") && !request.getPassword().equals("password")) {
            return ResponseEntity.status(401).body("");
        }

        Date now = new Date();

        Instant in30min = ChronoUnit.SECONDS.addTo(now.toInstant(), tokenDuration);

        String token = Jwts.builder()
                .setIssuer("ta")
                .setSubject("ta")
                .claim("username", request.getUsername())
                .setIssuedAt(now)
                .setExpiration(Date.from(in30min))
                .signWith(
                        SignatureAlgorithm.HS256, Base64.encodeBase64String(jwtKey.getBytes())
                )
                .compact();

        return ResponseEntity.ok(token);
    }

}
