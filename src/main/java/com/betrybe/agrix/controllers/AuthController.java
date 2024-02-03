package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.AuthRequestDto;
import com.betrybe.agrix.controllers.dto.AuthResponseDto;
import com.betrybe.agrix.services.SecurityTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Auth controller.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final SecurityTokenService securityTokenService;

  /**
   * Instantiates a new Auth controller.
   */
  public AuthController(
      AuthenticationManager authenticationManager, SecurityTokenService securityTokenService) {
    this.authenticationManager = authenticationManager;
    this.securityTokenService = securityTokenService;
  }

  /**
   * Login response entity.
   */
  @PostMapping("/login")
  public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        authRequestDto.username(),
        authRequestDto.password()
    );

    Authentication authentication = authenticationManager.authenticate(authToken);
    UserDetails person = (UserDetails) authentication.getPrincipal();
    String token = securityTokenService.createToken(person);

    return ResponseEntity.ok(new AuthResponseDto(token));
  }
}
