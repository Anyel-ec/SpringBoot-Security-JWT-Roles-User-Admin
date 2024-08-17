package top.anyel.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.anyel.jwt.models.dto.AuthRequestDTO;
import top.anyel.jwt.models.dto.JwtResponseDTO;
import top.anyel.jwt.security.JwtService;


import java.util.logging.Logger;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/api/v1/login")
    public JwtResponseDTO authenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );

        if (authentication.isAuthenticated()) {
            logger.info("genera token");
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(userDetails))
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid user request.");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/admin")
    public String admin() {
        // si no  el usuario tiene el rol de ADMIN, entonces podrá acceder a este recurso

        return "Hello Admin";
    }

}

