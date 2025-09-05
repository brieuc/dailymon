package com.brieuc.dailymon.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.brieuc.dailymon.entity.model.Model;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.repository.ModelRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {

      private final ModelRepository modelRepository;

      public List<Model> getModels() {
            return modelRepository.findAll();
      }

      public List<ModelFood> getFoodModels() {
      return modelRepository.findAllModelFood()
            .stream()
            .sorted(Comparator.comparing(ModelFood::getTitle))
            .collect(Collectors.toList());
      }

      public List<? extends Model> getFreeModels() {
            return modelRepository.findAllModelFree();
      }

      public List<? extends Model> getSportModels() {
            return modelRepository.findAllModelSport();      
      }

      public Optional<Model> getModelById(@NonNull UUID modelId) {
            return modelRepository.findById(modelId);
      }
      
      public Model createModel(Model newModel) {
            modelRepository.save(newModel);
            return newModel;
      }

      public Model updateModel(Model updatedModel) {
            if (!modelRepository.existsById(updatedModel.getId())) {
                  throw new RuntimeException("Model to update not found");
            }
            return modelRepository.save(updatedModel);
      }

      public void deleteModel(UUID id) {
            Model model = modelRepository.findById(id)
                  .orElseThrow(() -> new EntityNotFoundException("Model not found with id: " + id));
            
            try {
                  modelRepository.delete(model);
            } catch (DataIntegrityViolationException e) {
                  throw new IllegalStateException("Cannot delete model with id " + id + " - it may be referenced by other entities", e);
            }
      }
}
