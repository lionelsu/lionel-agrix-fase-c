package com.betrybe.agrix.security;

import com.betrybe.agrix.ebytr.staff.service.PersonService;
import com.betrybe.agrix.services.SecurityTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Security filter.
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private PersonService personService;

  @Autowired
  private SecurityTokenService securityTokenService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
  ) throws ServletException, IOException {
    String token = recoverToken(request);

    if (token != null) {
      String subject = securityTokenService.validateToken(token);
      UserDetails userDetails = personService.getPersonByUsername(subject);

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          userDetails, null, userDetails.getAuthorities());
    }
    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if (token == null) {
      return null;
    }
    return token.replace("Bearer ", "");
  }
}
