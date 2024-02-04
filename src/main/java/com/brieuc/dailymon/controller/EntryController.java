package com.brieuc.dailymon.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brieuc.dailymon.dto.EntryDto;
import com.brieuc.dailymon.entity.Entry;
import com.brieuc.dailymon.service.EntryService;
import com.brieuc.dailymon.service.ModelService;

@RequestMapping(value = "entry")
@RestController
public class EntryController {

    private EntryService entryService;
    private ModelService modelService;

    @Autowired
    public EntryController(EntryService entryService, ModelService modelService) {
        this.entryService = entryService;
        this.modelService = modelService;
    }
/*
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
*/

    @PutMapping("{id}")
    @ResponseBody
    public EntryDto updateEntry(@PathVariable UUID id, @RequestBody EntryDto entryDto) {
        Entry entry = this.entryService.updateEntry(
            this.entryService.getEntryById(id).get(),
            entryDto.getDescription(),
            entryDto.getQuantity()
        );
        return toDto(entry);
    }

    @PostMapping
    @ResponseBody
    public EntryDto createEntry(@RequestBody EntryDto entryDto) {
        System.out.println(entryDto.getModelId());
        Entry entry = this.entryService.createEntry(
            this.modelService.getModelById(entryDto.getModelId()).get(),
            entryDto.getDate(),
            entryDto.getDescription(),
            entryDto.getQuantity()
        );
        return toDto(entry);
    }

    @GetMapping("/{date}")
    @ResponseBody
    public List<EntryDto> getEntries(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return this.entryService.getEntriesByDate(date).stream().map(this::toDto).toList();
    }

    @GetMapping("/firstDate")
    public LocalDate getFirstDate() {
        List<EntryDto> entries = this.entryService.getEntries().stream().map(this::toDto).toList();
        return entries.stream().map(EntryDto::getDate).min(Comparator.naturalOrder()).orElse(LocalDate.now());
    }


    @GetMapping
    @ResponseBody
    public List<EntryDto> getEntries() {
        return this.entryService.getEntries().stream().map(this::toDto).toList();
    }

    private EntryDto toDto(Entry entry) {
        EntryDto entryDto = new EntryDto();
        entryDto.setId(entry.getId());
        entryDto.setDescription(entry.getDescription() == null ?
            entry.getModel().getLabel() : entry.getDescription());
        entryDto.setQuantity(entry.getQuantity());
        entryDto.setDate(entry.getDate());
        entryDto.setModelId(entry.getModel().getId());
        return entryDto;
    }
}
