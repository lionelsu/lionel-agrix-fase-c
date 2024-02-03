package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * Person response DTO.
 */
public record PersonResponseDto(Long id, String username, Role role) {

  /**
   * Constructor.
   */
  public static PersonResponseDto toDto(Person person) {
    return new PersonResponseDto(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }
}
