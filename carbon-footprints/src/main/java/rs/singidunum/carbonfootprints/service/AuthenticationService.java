package rs.singidunum.carbonfootprints.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rs.singidunum.carbonfootprints.controller.dto.request.AuthenticationRequest;
import rs.singidunum.carbonfootprints.controller.dto.request.RegisterRequest;
import rs.singidunum.carbonfootprints.controller.dto.response.AuthenticationResponse;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
