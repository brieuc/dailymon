package com.brieuc.dailymon.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
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
import com.brieuc.dailymon.CreateEntry;
import com.brieuc.dailymon.UpdateEntry;
import com.brieuc.dailymon.dto.EntryDto;
import com.brieuc.dailymon.dto.EntryFoodDto;
import com.brieuc.dailymon.dto.EntryFreeDto;
import com.brieuc.dailymon.dto.EntryFreeFoodDto;
import com.brieuc.dailymon.dto.EntrySportDto;
import com.brieuc.dailymon.dto.SummaryInfoDto;
import com.brieuc.dailymon.entity.entry.Entry;
import com.brieuc.dailymon.mapper.EntryMapper;
import com.brieuc.dailymon.service.EntryFacade;
import com.brieuc.dailymon.service.ModelFacade;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/entry")
public class EntryController {

    private final EntryFacade entryFacade;
    private final ModelFacade modelFacade;
    private final EntryMapper entryMapper;
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

    @PutMapping("/{id}/food")
    // @ResponseBody no need it's included in @RestController
    public EntryDto updateFoodEntry(@PathVariable UUID id, @RequestBody EntryFoodDto entryFoodDto) {
        Entry entry = this.entryFacade.updateEntry(entryFoodDto);
        return entryMapper.toDto(entry);
    }

    @PostMapping(value = "/food")
    public EntryDto createEntry(@RequestBody EntryFoodDto entryFoodDto) {
        Entry entry = this.entryFacade.createEntry(entryFoodDto);
        return entryMapper.toDto(entry);
    }

    @PostMapping(value = "/free/food")
    public EntryDto createEntry(@RequestBody EntryFreeFoodDto entryFreeFoodDto) {
        Entry entry = this.entryFacade.createEntry(entryFreeFoodDto);
        return entryMapper.toDto(entry);
    }

    @PutMapping("/{id}/free/food")
    // @ResponseBody no need it's included in @RestController
    public EntryDto updateFreeFoodEntry(@PathVariable UUID id, @Validated(UpdateEntry.class) @RequestBody EntryFreeFoodDto entryFreeFoodDto) {
        Entry entry = this.entryFacade.updateEntry(entryFreeFoodDto);
        return entryMapper.toDto(entry);
    }

    @PutMapping("/{id}/sport")
    // @ResponseBody no need it's included in @RestController
    public EntryDto updateSportEntry(@PathVariable UUID id, @Validated(UpdateEntry.class) @RequestBody EntrySportDto entrySportDto) {
        Entry entry = this.entryFacade.updateEntry(entrySportDto);
        return entryMapper.toDto(entry);
    }

    @PostMapping(value = "/sport")
    public EntryDto createEntry(@Validated(CreateEntry.class) @RequestBody EntrySportDto entrySportDto) {
        Entry entry = this.entryFacade.createEntry(entrySportDto);
        return entryMapper.toDto(entry);
    }

    @PutMapping("/{id}/free")
    // @ResponseBody no need it's included in @RestController
    public EntryDto updateFreeEntry(@PathVariable UUID id, @Validated(UpdateEntry.class) @RequestBody EntryFreeDto entryFreeDto) {
        // TODO il manque le check avec l'id de @PathVariable et la DTO
        Entry entry = this.entryFacade.updateEntry(entryFreeDto);
        return entryMapper.toDto(entry);
    }

    @PostMapping(value = "/free")
    public EntryDto createEntry(@Validated(CreateEntry.class) @RequestBody EntryFreeDto entryFreeDto) {
        Entry entry = this.entryFacade.createEntry(entryFreeDto);
        return entryMapper.toDto(entry);
    }


    @GetMapping("/{date}/food")
    public List<EntryDto> getFoodEntries(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return this.entryFacade.getFoodEntriesByDate(date).stream().map(e -> entryMapper.toDto(e)).toList();
    }

    @GetMapping(value = "/firstDate", produces = MediaType.APPLICATION_JSON_VALUE)
    // TODO without using EntryDTO, surely a better way
    public List<String> getFirstDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = entryFacade.getMinEntryDate().format(formatter);
        return List.of(date);
    }

    @GetMapping("/{date}")
    public List<EntryDto> getEntries(@PathVariable @NotNull(message = "Date cannot be null") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return this.entryFacade.getEntriesByDate(date).stream().map(e -> entryMapper.toDto(e)).toList();
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

        HashMap<String, Double> map = entryFacade.getSummaryInfo(fromDate, toDate);
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
        entryFacade.deleteEntry(id);
    }
}
