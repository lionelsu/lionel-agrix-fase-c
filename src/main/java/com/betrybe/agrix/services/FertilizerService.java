package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


/**
 * The Fertilizer service.
 */
@Service
public class FertilizerService {
  private final FertilizerRepository fertilizerRepository;

  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  public Fertilizer create(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  /**
   * Gets fertilizer by id.
   */
  public Fertilizer getFertilizerById(Long fertilizerId) {
    Optional<Fertilizer> fertilizer = fertilizerRepository.findById(fertilizerId);
    if (fertilizer.isEmpty()) {
      throw new FertilizerNotFoundException();
    }
    return fertilizer.get();
  }
}
