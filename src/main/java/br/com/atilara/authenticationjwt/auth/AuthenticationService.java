package br.com.atilara.authenticationjwt.auth;

import br.com.atilara.authenticationjwt.config.JwtService;
import br.com.atilara.authenticationjwt.token.TokenModel;
import br.com.atilara.authenticationjwt.token.TokenRepository;
import br.com.atilara.authenticationjwt.token.TokenTypeEnum;
import br.com.atilara.authenticationjwt.user.RoleEnum;
import br.com.atilara.authenticationjwt.user.UserModel;
import br.com.atilara.authenticationjwt.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepository;

    private void saveUserToken(UserModel user, String token) {
        var userToken = TokenModel.builder()
                .token(token)
                .tokenType(TokenTypeEnum.BEARER)
                .user(user)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(userToken);
    }

    private void revokeAllUserTokens(UserModel user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUserId(user.getId());
        validUserTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = UserModel.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        var savedUser = userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        revokeAllUserTokens(savedUser);
        saveUserToken(savedUser, jwt);
        return AuthenticationResponse.builder().jwt(jwt).build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User or password incorrect"));
        var jwt = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwt);
        return AuthenticationResponse.builder().jwt(jwt).build();
    }

}
