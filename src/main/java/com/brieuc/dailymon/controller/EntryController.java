package com.brieuc.dailymon.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.brieuc.dailymon.dto.EntryDto;
import com.brieuc.dailymon.dto.EntryFoodDto;
import com.brieuc.dailymon.dto.EntryFreeDto;
import com.brieuc.dailymon.dto.EntrySportDto;
import com.brieuc.dailymon.entity.entry.Entry;
import com.brieuc.dailymon.entity.entry.EntryFood;
import com.brieuc.dailymon.entity.entry.EntryFree;
import com.brieuc.dailymon.entity.entry.EntrySport;
import com.brieuc.dailymon.service.EntryFacade;
import com.brieuc.dailymon.service.ModelFacade;

@RestController
@RequestMapping(value = "/entry")
public class EntryController {

    private EntryFacade entryFacade;
    private ModelFacade modelFacade;

    @Autowired
    public EntryController(EntryFacade entryFacade, ModelFacade modelFacade) {
        this.entryFacade = entryFacade;
        this.modelFacade = modelFacade;
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

    @PutMapping("/{id}/food")
    // @ResponseBody no need it's included in @RestController
    public EntryDto updateFoodEntry(@PathVariable UUID id, @RequestBody EntryFoodDto entryFoodDto) {
        Entry entry = this.entryFacade.updateEntry(entryFoodDto);
        return toDto(entry);
    }

    @PostMapping(value = "/food")
    public EntryDto createEntry(@RequestBody EntryFoodDto entryFoodDto) {
        Entry entry = this.entryFacade.createEntry(entryFoodDto);
        return toDto(entry);
    }

    @PutMapping("/{id}/sport")
    // @ResponseBody no need it's included in @RestController
    public EntryDto updateSportEntry(@PathVariable UUID id, @RequestBody EntrySportDto entrySportDto) {
        Entry entry = this.entryFacade.updateEntry(entrySportDto);
        return toDto(entry);
    }

    @PostMapping(value = "/sport")
    public EntryDto createEntry(@RequestBody EntrySportDto entrySportDto) {
        Entry entry = this.entryFacade.createEntry(entrySportDto);
        return toDto(entry);
    }

    @PutMapping("/{id}/free")
    // @ResponseBody no need it's included in @RestController
    public EntryDto updateFreeEntry(@PathVariable UUID id, @RequestBody EntryFreeDto entryFreeDto) {
        Entry entry = this.entryFacade.updateEntry(entryFreeDto);
        return toDto(entry);
    }

    @PostMapping(value = "/free")
    public EntryDto createEntry(@RequestBody EntryFreeDto entryFreeDto) {
        Entry entry = this.entryFacade.createEntry(entryFreeDto);
        return toDto(entry);
    }


    @GetMapping("/{date}/food")
    public List<EntryDto> getFoodEntries(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return this.entryFacade.getFoodEntriesByDate(date).stream().map(this::toDto).toList();
    }

    @GetMapping(value = "/firstDate", produces = MediaType.APPLICATION_JSON_VALUE)
    // TODO without using EntryDTO, surely a better way
    public EntryDto getFirstDate() {
        EntryDto entryDto = new EntryFoodDto();
        entryDto.setDate(entryFacade.getMinEntryDate());
        return entryDto;
    }

    @GetMapping("/{date}")
    public List<EntryDto> getEntries(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return this.entryFacade.getEntriesByDate(date).stream().map(this::toDto).toList();
    }
 

    //http://localhost:8080/entry/2024-05-11?numberOfDays=7
    @GetMapping(value = "/get/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getRelevantDates(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                    @RequestParam(name = "numberOfDays") Integer nbOfDays) {
        
        List<String> listOfDates = new ArrayList<>();
        LocalDate minDate = entryFacade.getMinEntryDate();
        DayOfWeek dayOfWeek = minDate.getDayOfWeek();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        int iDay = dayOfWeek.getValue();
        // LUNDI, MARDI, MERCERDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE
        //   1      2        3       4        5       6        7
        // Si Dimanche (7), pour trouver le lundi, in fait Date - 6 (iDay - 1)
        // Si Mercredi (3), pour trouver le lundi, on fait Date - 2 (iDay - 1)
        // Si Lundi (1), pour trouver le lundi, on fait Date - 0 (iDay - 1)
        int i = 1;
        int dayToSubstract = iDay - 1;
        LocalDate firstDay = minDate.minusDays(dayToSubstract);
        LocalDate currentDay = firstDay;
        while (currentDay.isBefore(LocalDate.now()) ) {
            listOfDates.add(currentDay.format(formatter));
            currentDay = firstDay.plusDays(i * nbOfDays);
            i++;
        }
        return listOfDates;
    }

    // the name = "id" is not mandatory if the variable has the same name though.
    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable(name = "id") UUID id) {
        entryFacade.deleteEntry(id);
    }

    private EntryDto toDto(Entry entry) {
        if (entry instanceof EntryFood entryFood) {
            EntryFoodDto entryDto = new EntryFoodDto();
            entryDto.setId(entryFood.getId());
            entryDto.setTitle(entryFood.getTitle());
            entryDto.setDescription(entry.getDescription() == null ?
            entryFood.getModel().getTitle() : entry.getDescription());
            entryDto.setQuantity(entryFood.getQuantity());
            entryDto.setDate(entryFood.getDate());
            entryDto.setModelId(entryFood.getModel().getId());
            return entryDto;
        }
        if (entry instanceof EntrySport entrySport) {
            EntrySportDto entryDto = new EntrySportDto();
            entryDto.setId(entry.getId());
            entryDto.setTitle(entrySport.getTitle());
            entryDto.setDescription(entrySport.getDescription());
            entryDto.setDate(entrySport.getDate());
            entryDto.setModelId(entrySport.getModel().getId());
            entryDto.setAerobic(entrySport.getAerobic());
            entryDto.setAnaerobic(entrySport.getAnaerobic());
            entryDto.setDuration(entrySport.getDuration());
            entryDto.setBenefit(entrySport.getBenefit());
            entryDto.setKcal(entrySport.getKcal());
            return entryDto;
        }
        if (entry instanceof EntryFree entryFree) {
            EntryFreeDto entryDto = new EntryFreeDto();
            entryDto.setId(entryFree.getId());
            entryDto.setTitle(entryFree.getTitle());
            entryDto.setDescription(entry.getDescription());
            entryDto.setDate(entryFree.getDate());
            entryDto.setModelId(entryFree.getModel().getId());
            return entryDto;
        }
        return null;
    }
}
