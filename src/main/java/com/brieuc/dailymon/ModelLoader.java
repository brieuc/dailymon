package com.brieuc.dailymon;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.brieuc.dailymon.entity.Entry;
import com.brieuc.dailymon.entity.Model;
import com.brieuc.dailymon.repository.EntryRepository;
import com.brieuc.dailymon.repository.ModelRepository;

@Component
public class ModelLoader implements ApplicationRunner {

    private ModelRepository modelRepository;
    private EntryRepository entryRepository;

    @Autowired
    ModelLoader(ModelRepository modelRepository, EntryRepository entryRepository) {
        this.modelRepository = modelRepository;
        this.entryRepository = entryRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (this.modelRepository.count() > 0) {
            return;
        }

        Model blueMoon = new Model();
        blueMoon.setDescription("Bluemoon 1DL");
        blueMoon.setLabel("Bluemoon 1DL");
        blueMoon.setKcal(47);
        this.modelRepository.save(blueMoon);

        Model inglewood = new Model();
        inglewood.setDescription("Inglewood Honey Goat");
        inglewood.setLabel("Inglewood");
        inglewood.setKcal(1000);
        this.modelRepository.save(inglewood);

        Model pizza = new Model();
        pizza.setDescription("Pizza");
        pizza.setLabel("Pizza");
        pizza.setKcal(1200);
        this.modelRepository.save(pizza);

        Model mcDonald = new Model();
        mcDonald.setLabel("Menu Big Mac");
        mcDonald.setDescription("Menu Big Mac Medium");
        mcDonald.setKcal(850);
        this.modelRepository.save(mcDonald);


        Model mcFleuryRagusa = new Model();
        mcFleuryRagusa.setLabel("McFlurry");
        mcFleuryRagusa.setDescription("McFlurry");
        mcFleuryRagusa.setKcal(540);
        this.modelRepository.save(mcFleuryRagusa);

        Model brancheCailler = new Model();
        brancheCailler.setLabel("Branche Cailler");
        brancheCailler.setDescription("Branche cailler");
        brancheCailler.setKcal(160);
        this.modelRepository.save(brancheCailler);

        Model carreChocolat = new Model();
        carreChocolat.setLabel("Carré de chocolat");
        carreChocolat.setDescription("Carré de chocolat cailler");
        carreChocolat.setKcal(45);
        this.modelRepository.save(carreChocolat);
    }
}
