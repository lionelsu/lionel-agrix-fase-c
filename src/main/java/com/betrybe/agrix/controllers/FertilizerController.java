package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  @PostMapping
  public ResponseEntity<FertilizerDto> create(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer fertilizer = fertilizerService.create(fertilizerDto.toFertilizer());
    return ResponseEntity.status(HttpStatus.CREATED).body(FertilizerDto.toDto(fertilizer));
  }

  /**
   * Gets fertilizer.
   */
  @GetMapping("/{fertilizerId}")
  public ResponseEntity<FertilizerDto> getFertilizer(@PathVariable Long fertilizerId) {
    Fertilizer fertilizer = fertilizerService.getFertilizerById(fertilizerId);
    FertilizerDto fertilizerDto = FertilizerDto.toDto(fertilizer);
    return ResponseEntity.ok(fertilizerDto);
  }

  /**
   * Gets all fertilizers.
   *
   * @return the all fertilizers
   */
  @GetMapping
  public ResponseEntity<List<FertilizerDto>> getAllFertilizers() {
    List<Fertilizer> fertilizers = fertilizerService.getAllFertilizers();
    List<FertilizerDto> fertilizerDto = fertilizers.stream().map(FertilizerDto::toDto).toList();
    return ResponseEntity.ok(fertilizerDto);
  }
}
