package com.brieuc.dailymon;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "entry")
@RestController
public class EntryController {

    @GetMapping("")
    @ResponseBody
    public List<EntryDto> getEntries(@RequestParam Date fromDate, @RequestParam Date toDate) {
        return null;
    }
}
