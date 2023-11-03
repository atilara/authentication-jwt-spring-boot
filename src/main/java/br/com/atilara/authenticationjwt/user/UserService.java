package br.com.atilara.authenticationjwt.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public void changePassword(ChangePasswordRequest request, Principal loggedInUser) {
        var user = (UserModel) ((UsernamePasswordAuthenticationToken) loggedInUser).getPrincipal();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        } else if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new RuntimeException("New password and confirm new password must be the same");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

    }
}
