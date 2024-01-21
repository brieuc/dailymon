package com.brieuc.dailymon.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brieuc.dailymon.dto.EntryDto;
import com.brieuc.dailymon.entity.Entry;
import com.brieuc.dailymon.service.EntryService;

@RequestMapping(value = "entry")
@RestController
public class EntryController {

    private EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping("/search")
    @ResponseBody
    public HashMap<LocalDate, List<EntryDto>> getEntries( @RequestParam LocalDate fromDate,
                                                    @RequestParam LocalDate toDate) {
        
        int NbDays = 30, i = 0;
        HashMap<LocalDate, List<EntryDto>> map = new HashMap<>();

        LocalDate currentDate = LocalDate.now();
        for (i=0; i<=NbDays; i++) {
            currentDate = currentDate.minusDays(i);
            map.put(currentDate, this.entryService.getEntriesByDate(currentDate).stream().map(this::toDto).toList());
        }
        return map;
    }

    @GetMapping("/{date}")
    public List<EntryDto> getEntries(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return this.entryService.getEntriesByDate(date).stream().map(this::toDto).toList();
    }

    private EntryDto toDto(Entry entity) {
        EntryDto entryDto = new EntryDto();
        entryDto.setDescription(entity.getDescription());
        entryDto.setQuantity(entity.getQuantity());
        entryDto.setDate(entity.getDate());
        entryDto.setModelId(entity.getModel().getId());
        return entryDto;
    }
}
