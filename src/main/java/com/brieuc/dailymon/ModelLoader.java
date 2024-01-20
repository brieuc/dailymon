package com.brieuc.dailymon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.brieuc.dailymon.entity.Model;

@Component
public class ModelLoader implements ApplicationRunner {

    private ModelRepository modelRepository;

    @Autowired
    ModelLoader(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (this.modelRepository.count() != 0) {
            return;
        }
        Model brancheCailler = new Model();
        brancheCailler.setLabel("Branche Cailler");
        brancheCailler.setDescription("Branche cailler");
        brancheCailler.setKcal(160);
        this.modelRepository.save(brancheCailler);
    }
    
}
