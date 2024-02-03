package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {
  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Gets crop by id.
   *
   * @param cropId the crop id
   * @return the crop by id
   */
  public Crop getCropById(Long cropId) {
    Optional<Crop> cropOptional = cropRepository.findById(cropId);
    if (cropOptional.isEmpty()) {
      throw new CropNotFoundException();
    }

    return cropOptional.get();
  }

  /**
   * Add fertilizer to crop.
   */
  public void addFertilizerToCrop(Long cropId, Long fertilizerId) {
    Optional<Crop> cropOptional = cropRepository.findById(cropId);
    if (cropOptional.isEmpty()) {
      throw new CropNotFoundException();
    }

    Optional<Fertilizer> fertilizerOptional = fertilizerRepository.findById(fertilizerId);
    if (fertilizerOptional.isEmpty()) {
      throw new FertilizerNotFoundException();
    }

    Crop crop = cropOptional.get();
    Fertilizer fertilizer = fertilizerOptional.get();
    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);
  }

  public List<Fertilizer> getFertilizersByCropId(Long cropId) {
    Crop crop = cropRepository.findById(cropId).orElseThrow(CropNotFoundException::new);
    return crop.getFertilizers();
  }

  public List<Crop> findByPlantingDateBetween(LocalDate startDate, LocalDate endDate) {
    return cropRepository.findByHarvestDateBetween(startDate, endDate);
  }
}
