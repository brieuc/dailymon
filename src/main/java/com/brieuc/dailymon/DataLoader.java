package com.brieuc.dailymon;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.brieuc.dailymon.entity.Sport;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.entity.model.ModelFree;
import com.brieuc.dailymon.entity.model.ModelSport;
import com.brieuc.dailymon.repository.EntrySportRepository;
import com.brieuc.dailymon.repository.ModelFoodRepository;
import com.brieuc.dailymon.repository.ModelFreeRepository;
import com.brieuc.dailymon.repository.ModelSportRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private ModelFoodRepository modelFoodRepository;
    private ModelSportRepository modelSportRepository;
    private ModelFreeRepository modelFreeRepository;
    private EntrySportRepository entrySportRepository;

    @Autowired
    DataLoader(ModelFoodRepository modelFoodRepository, ModelSportRepository modelSportRepository,
                ModelFreeRepository modelFreeRepository, EntrySportRepository entrySportRepository) {
        this.modelFoodRepository = modelFoodRepository;
        this.modelSportRepository = modelSportRepository;
        this.modelFreeRepository = modelFreeRepository;
        this.entrySportRepository = entrySportRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (this.modelFoodRepository.count() > 0) {
            return;
        }

        ModelSport fitVaudoise = ModelSport.builder().sport(Sport.HIIT).build();
        fitVaudoise.setTitle("Fit VA");
        fitVaudoise.setDescription("Salle de Fit Vaudoise");
        modelSportRepository.save(fitVaudoise);

        ModelSport lbi = ModelSport.builder().sport(Sport.BOXING).build();
        lbi.setTitle("LBI");
        lbi.setDescription("LBI");
        modelSportRepository.save(lbi);

        ModelSport fitTerrainBasket = ModelSport.builder().sport(Sport.HIIT).build();
        fitTerrainBasket.setTitle("Terrain de Basket");
        fitTerrainBasket.setDescription("Terrain de Basket");
        modelSportRepository.save(fitTerrainBasket);

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
        EntrySport entryFit = new EntrySport(508, 56, 2.6, 2.4, Benefit.SPRINT, fitVaudoise);
        entryFit.setDate(LocalDate.of(7, 5, 2024));
        entryFit.setTitle("Fit VA");
        entryFit.setDescription("4 rounds de corde (break 3 x 20 pompes + 12 pompes diamand)");
          +
            "4 rounds shadow 30 sec + squat/fentes 30 sec" +
            "4 rounds intervalle training" +
            "12 explosif avec barre" +
            "12 explosif barre biceps" +
            "25 jumpy jack triceps" +
            "24 coups en hauteur avec haltères violettes" +
            "12 sauts latéraux au-dessud du steppeur" +
            "30 sec planche active" +
            "12 TRX dos"
        );
        entrySportRepository.save(entryFit);
        */

        ModelFood inglewood = new ModelFood();
        inglewood.setDescription("Inglewood Honey Goat");
        inglewood.setTitle("Inglewood");
        inglewood.setKcal(1000);
        this.modelFoodRepository.save(inglewood);

        ModelFood pizza = new ModelFood();
        pizza.setDescription("Pizza");
        pizza.setTitle("Pizza");
        pizza.setKcal(1200);
        this.modelFoodRepository.save(pizza);

        ModelFood mcDonald = new ModelFood();
        mcDonald.setTitle("Menu Big Mac");
        mcDonald.setDescription("Menu Big Mac Medium");
        mcDonald.setKcal(850);
        this.modelFoodRepository.save(mcDonald);


        ModelFood mcFleuryRagusa = new ModelFood();
        mcFleuryRagusa.setTitle("McFlurry");
        mcFleuryRagusa.setDescription("McFlurry");
        mcFleuryRagusa.setKcal(540);
        this.modelFoodRepository.save(mcFleuryRagusa);

        ModelFood brancheCailler = new ModelFood();
        brancheCailler.setTitle("Branche Cailler");
        brancheCailler.setDescription("Branche cailler");
        brancheCailler.setKcal(160);
        this.modelFoodRepository.save(brancheCailler);

        ModelFood carreChocolat = new ModelFood();
        carreChocolat.setTitle("Carré de chocolat");
        carreChocolat.setDescription("Carré de chocolat cailler");
        carreChocolat.setKcal(45);
        this.modelFoodRepository.save(carreChocolat);
        
    }
}
