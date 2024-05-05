package com.brieuc.dailymon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.brieuc.dailymon.entity.Sport;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.entity.model.ModelFree;
import com.brieuc.dailymon.entity.model.ModelSport;
import com.brieuc.dailymon.repository.ModelFoodRepository;
import com.brieuc.dailymon.repository.ModelFreeRepository;
import com.brieuc.dailymon.repository.ModelSportRepository;

@Component
public class ModelLoader implements ApplicationRunner {

    private ModelFoodRepository modelFoodRepository;
    private ModelSportRepository modelSportRepository;
    private ModelFreeRepository modelFreeRepository;

    @Autowired
    ModelLoader(ModelFoodRepository modelFoodRepository, ModelSportRepository modelSportRepository,
                ModelFreeRepository modelFreeRepository) {
        this.modelFoodRepository = modelFoodRepository;
        this.modelSportRepository = modelSportRepository;
        this.modelFreeRepository = modelFreeRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (this.modelFoodRepository.count() > 0) {
            return;
        }

        ModelSport course = ModelSport.builder().sport(Sport.RUNNING).build();
        course.setTitle("Course");
        course.setDescription("Course");
        modelSportRepository.save(course);

        ModelSport skate = ModelSport.builder().sport(Sport.SKATE).build();
        skate.setTitle("Skate");
        skate.setDescription("Skate");
        modelSportRepository.save(skate);
        

        ModelFree free = new ModelFree();
        free.setDescription("Free");
        free.setTitle("Free");
        this.modelFreeRepository.save(free);

        ModelFood blueMoon = new ModelFood();
        blueMoon.setDescription("Bluemoon 1DL");
        blueMoon.setTitle("Bluemoon 1DL");
        blueMoon.setKcal(47);
        this.modelFoodRepository.save(blueMoon);
/*
        ModelFood inglewood = new ModelFood();
        inglewood.setDescription("Inglewood Honey Goat");
        inglewood.setLabel("Inglewood");
        inglewood.setKcal(1000);
        this.modelRepository.save(inglewood);

        ModelFood pizza = new ModelFood();
        pizza.setDescription("Pizza");
        pizza.setLabel("Pizza");
        pizza.setKcal(1200);
        this.modelRepository.save(pizza);

        ModelFood mcDonald = new ModelFood();
        mcDonald.setLabel("Menu Big Mac");
        mcDonald.setDescription("Menu Big Mac Medium");
        mcDonald.setKcal(850);
        this.modelRepository.save(mcDonald);


        ModelFood mcFleuryRagusa = new ModelFood();
        mcFleuryRagusa.setLabel("McFlurry");
        mcFleuryRagusa.setDescription("McFlurry");
        mcFleuryRagusa.setKcal(540);
        this.modelRepository.save(mcFleuryRagusa);

        ModelFood brancheCailler = new ModelFood();
        brancheCailler.setLabel("Branche Cailler");
        brancheCailler.setDescription("Branche cailler");
        brancheCailler.setKcal(160);
        this.modelRepository.save(brancheCailler);

        ModelFood carreChocolat = new ModelFood();
        carreChocolat.setLabel("Carré de chocolat");
        carreChocolat.setDescription("Carré de chocolat cailler");
        carreChocolat.setKcal(45);
        this.modelRepository.save(carreChocolat);
        */
        
    }
}
