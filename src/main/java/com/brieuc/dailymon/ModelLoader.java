package com.brieuc.dailymon;

import java.time.LocalDate;

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
        if (this.modelRepository.count() != 1) {
            //return;
        }
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

        Entry entry20012024 = new Entry();
        entry20012024.setDate(LocalDate.now());
        entry20012024.setDescription("Petit carré discret");
        entry20012024.setModel(carreChocolat);
        entry20012024.setQuantity(2.0);
        this.entryRepository.save(entry20012024);

        Entry entry20012024_2 = new Entry();
        entry20012024_2.setDate(LocalDate.now());
        entry20012024_2.setDescription("Branche migros");
        entry20012024_2.setModel(brancheCailler);
        entry20012024_2.setQuantity(1.0);
        this.entryRepository.save(entry20012024_2);

        Entry entry19012024 = new Entry();
        entry19012024.setDate(LocalDate.now().minusDays(1));
        entry19012024.setDescription("Chocolat noir");
        entry19012024.setModel(carreChocolat);
        entry19012024.setQuantity(1.0);
        this.entryRepository.save(entry19012024);
    }
}
