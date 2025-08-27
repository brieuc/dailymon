package com.brieuc.dailymon.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brieuc.dailymon.CommonUtil;
import com.brieuc.dailymon.dto.EntryDto;
import com.brieuc.dailymon.dto.EntryFoodDto;
import com.brieuc.dailymon.dto.SummaryInfoDto;
import com.brieuc.dailymon.entity.entry.Entry;
import com.brieuc.dailymon.mapper.EntryMapper;
import com.brieuc.dailymon.service.EntryService;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/entry")
public class EntryController {

    private final EntryService entryService;
    private final EntryMapper entryMapper;

    @PostMapping
    public EntryDto createEntry(@RequestBody EntryDto entryDto) {
        Entry entry = entryService.createEntry(entryMapper.toEntity(entryDto));
        return entryMapper.toDto(entry);
    }

    @PutMapping("/{id}")
    // @ResponseBody no need it's included in @RestController
    public EntryDto updateEntry(@PathVariable UUID id, @RequestBody EntryFoodDto entryFoodDto) {
        Entry entry = entryService.updateEntry(entryMapper.toEntity(entryFoodDto));
        return entryMapper.toDto(entry);
    }

    @GetMapping(value = "/firstDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getFirstDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = entryService.getMinEntryDate().format(formatter);
        return List.of(date);
    }

    @GetMapping("/{date}")
    public List<EntryDto> getEntries(@PathVariable @NotNull(message = "Date cannot be null") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return entryService.getEntriesByDate(date).stream().map(e -> entryMapper.toDto(e)).toList();
    }
 

    //http://localhost:8080/entry/get/2024-05-11?numberOfDays=7
    @GetMapping(value = "/get/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getRelevantDates(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                    @RequestParam(name = "numberOfDays") Integer nbOfDays) {
        
        //return CommonUtil.getListOfDates(entryFacade.getMinEntryDate(), nbOfDays);
        List<String> s = CommonUtil.getListOfDates(date, nbOfDays);
        return s;
    }

    @GetMapping(value = "/summary-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public SummaryInfoDto getSummaryInfo(@RequestParam(name = "fromDate") LocalDate fromDate,
                                         @RequestParam(name = "toDate") LocalDate toDate) {

        HashMap<String, Double> map = entryService.getSummaryInfo(fromDate, toDate);
        SummaryInfoDto summaryInfoDto = new SummaryInfoDto();
        summaryInfoDto.setIngestedKcal(map.get("ingestedKcal"));
        summaryInfoDto.setSpentKcal(map.get("spentKcal"));
        summaryInfoDto.setSportDuration(map.get("sportDuration"));
        summaryInfoDto.setDrinkingBeer(map.get("drinkingBeer"));
        summaryInfoDto.setAerobic(map.get("aerobic"));
        summaryInfoDto.setAnaerobic(map.get("anaerobic"));
        return summaryInfoDto;
    }

    // the name = "id" is not mandatory if the variable has the same name though.
    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable(name = "id") UUID id) {
        entryService.deleteEntry(id);
    }
}
