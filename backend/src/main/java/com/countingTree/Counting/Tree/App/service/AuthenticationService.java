package com.countingTree.Counting.Tree.App.service;

import com.countingTree.Counting.Tree.App.dto.auth.AuthenticationRequest;
import com.countingTree.Counting.Tree.App.dto.auth.AuthenticationResponse;
import com.countingTree.Counting.Tree.App.dto.auth.RegisterRequest;
import com.countingTree.Counting.Tree.App.model.Role;
import com.countingTree.Counting.Tree.App.model.User;
import com.countingTree.Counting.Tree.App.repository.UserRepository;
import com.countingTree.Counting.Tree.App.service.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null ? request.getRole() : Role.USER)
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(new UserDetailsImpl(user));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        var user = repository.findByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(new UserDetailsImpl(user));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
