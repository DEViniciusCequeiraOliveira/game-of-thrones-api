package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Repository.UserRepository;
import com.vinicius.gameofthrones.dto.LoginRequest;
import com.vinicius.gameofthrones.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimAccessor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class TokenController {
    @Autowired
    private JwtEncoder encoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/Login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        var endity = userRepository.findByEmail(loginRequest.email());
        if (endity.isEmpty() || !endity.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("");
        }
        var now = Instant.now();

        var expiresIn = 300L;

        var clains = JwtClaimsSet.builder()
                .issuer("Jefiroo.dev-vinicios.dev")
                .subject(endity.get().getEmail())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = encoder.encode(JwtEncoderParameters.from(clains)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue,expiresIn));
    }
}
