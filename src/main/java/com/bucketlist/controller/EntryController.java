package com.bucketlist.controller;

import com.bucketlist.model.Entry;
import com.bucketlist.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:9000")
public class EntryController {

    @Autowired
    EntryService entryService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Entry> getAllEntries() {
        return entryService.getAllEntries();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Entry createEntry(@RequestParam(value = "title", required = true) String title,
                             @RequestParam(value = "content", required = true) String content) {
        return entryService.createEntry(title, content);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteEntry(@RequestParam(value = "id", required = true) Long id) {
        entryService.deleteEntry(id);
    }
}
