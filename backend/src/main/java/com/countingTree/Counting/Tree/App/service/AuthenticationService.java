package com.countingTree.Counting.Tree.App.service;

import com.countingTree.Counting.Tree.App.dto.auth.AuthenticationRequest;
import com.countingTree.Counting.Tree.App.dto.auth.AuthenticationResponse;
import com.countingTree.Counting.Tree.App.dto.auth.RegisterRequest;

public interface AuthenticationService {
        AuthenticationResponse register(RegisterRequest request);

        AuthenticationResponse authenticate(AuthenticationRequest request);
}
