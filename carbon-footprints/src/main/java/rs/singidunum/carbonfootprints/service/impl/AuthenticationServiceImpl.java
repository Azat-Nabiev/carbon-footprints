package rs.singidunum.carbonfootprints.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.singidunum.carbonfootprints.controller.dto.request.AuthenticationRequest;
import rs.singidunum.carbonfootprints.controller.dto.request.RegisterRequest;
import rs.singidunum.carbonfootprints.controller.dto.response.AuthenticationResponse;
import rs.singidunum.carbonfootprints.model.Token;
import rs.singidunum.carbonfootprints.model.User;
import rs.singidunum.carbonfootprints.model.enums.Role;
import rs.singidunum.carbonfootprints.model.enums.TokenType;
import rs.singidunum.carbonfootprints.repository.TokenRepository;
import rs.singidunum.carbonfootprints.repository.UserRepository;
import rs.singidunum.carbonfootprints.service.AuthenticationService;
import rs.singidunum.carbonfootprints.service.JwtService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                       .firstName(request.getFirstName())
                       .lastName(request.getLastName())
                       .email(request.getEmail())
                       .password(passwordEncoder.encode(request.getPassword()))
                       .role(Role.USER)
                       .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                                     .accessToken(jwtToken)
                                     .refreshToken(refreshToken)
                                     .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                             .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                                     .userId(user.getId())
                                     .accessToken(jwtToken)
                                     .refreshToken(refreshToken)
                                     .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                           .user(user)
                           .token(jwtToken)
                           .tokenType(TokenType.BEARER)
                           .expired(false)
                           .revoked(false)
                           .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                                      .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                                                         .accessToken(accessToken)
                                                         .refreshToken(refreshToken)
                                                         .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
