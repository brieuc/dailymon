package com.brieuc.dailymon;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.brieuc.dailymon.entity.FoodType;
import com.brieuc.dailymon.entity.Sport;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.entity.model.ModelFree;
import com.brieuc.dailymon.entity.model.ModelSport;
import com.brieuc.dailymon.entity.user.Role;
import com.brieuc.dailymon.entity.user.User;
import com.brieuc.dailymon.repository.EntrySportRepository;
import com.brieuc.dailymon.repository.ModelFoodRepository;
import com.brieuc.dailymon.repository.ModelFreeRepository;
import com.brieuc.dailymon.repository.ModelSportRepository;
import com.brieuc.dailymon.repository.UserRepository;

import ch.qos.logback.core.model.Model;

@Component
public class DataLoader implements ApplicationRunner {

    private final ModelFoodRepository modelFoodRepository;
    private final ModelSportRepository modelSportRepository;
    private final ModelFreeRepository modelFreeRepository;
    private final EntrySportRepository entrySportRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    DataLoader(ModelFoodRepository modelFoodRepository, ModelSportRepository modelSportRepository,
                ModelFreeRepository modelFreeRepository, EntrySportRepository entrySportRepository,
                UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.modelFoodRepository = modelFoodRepository;
        this.modelSportRepository = modelSportRepository;
        this.modelFreeRepository = modelFreeRepository;
        this.entrySportRepository = entrySportRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (this.userRepository.count() == 0) {
            User user = User.builder()
                            .username("brieuc")
                            .password(passwordEncoder.encode("PasswdThunder1982fr_"))
                            .role(Role.ADMIN)
                            .build();
            this.userRepository.save(user);
        }

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
        
         ModelFree free = ModelFree.builder()
            .id(UUID.randomUUID()).build();
        free.setDescription("Free");
        free.setTitle("Free");
        this.modelFreeRepository.save(free);

         ModelFood blueMoon = ModelFood.builder()
            .id(UUID.randomUUID()).build();
        blueMoon.setDescription("Bluemoon 1DL");
        blueMoon.setTitle("Bluemoon 1DL");
        blueMoon.setKcal(47);
        blueMoon.setFoodType(FoodType.ALCOHOL);
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

        ModelFood inglewood = ModelFood.builder()
            .id(UUID.randomUUID()).build();
        inglewood.setDescription("Inglewood Honey Goat");
        inglewood.setTitle("Inglewood");
        inglewood.setKcal(1000);
        this.modelFoodRepository.save(inglewood);

        ModelFood pizza = ModelFood.builder()
            .id(UUID.randomUUID()).build();
        pizza.setDescription("Pizza");
        pizza.setTitle("Pizza");
        pizza.setKcal(1200);
        this.modelFoodRepository.save(pizza);

         ModelFood mcDonald = ModelFood.builder()
            .id(UUID.randomUUID()).build();
        mcDonald.setTitle("Menu Big Mac");
        mcDonald.setDescription("Menu Big Mac Medium");
        mcDonald.setKcal(850);
        this.modelFoodRepository.save(mcDonald);


         ModelFood mcFleuryRagusa = ModelFood.builder()
            .id(UUID.randomUUID()).build();
        mcFleuryRagusa.setTitle("McFlurry");
        mcFleuryRagusa.setDescription("McFlurry");
        mcFleuryRagusa.setKcal(540);
        this.modelFoodRepository.save(mcFleuryRagusa);

         ModelFood brancheCailler = ModelFood.builder()
            .id(UUID.randomUUID()).build();
        brancheCailler.setTitle("Branche Cailler");
        brancheCailler.setDescription("Branche cailler");
        brancheCailler.setKcal(160);
        this.modelFoodRepository.save(brancheCailler);


         ModelFood carreChocolat = ModelFood.builder()
            .id(UUID.randomUUID()).build();
        carreChocolat.setTitle("Carré de chocolat");
        carreChocolat.setDescription("Carré de chocolat cailler");
        carreChocolat.setKcal(45);
        this.modelFoodRepository.save(carreChocolat);
    }
}
