package com.bucketlist.controller;

import com.bucketlist.util.ListUtils;
import com.bucketlist.model.Entry;
import com.bucketlist.service.EntryService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @PostMapping("/move")
    public List<Entry> moveEntry(@RequestParam(value = "from", required = true) Long from,
                                     @RequestParam(value = "to", required = true) Long to) {
        return entryService.moveEntry(from, to);
    }

    @GetMapping()
    public List<Entry> getAllEntries() {
        return entryService.getAllEntries();
    }

    @PutMapping()
    public Entry createEntry(@RequestParam(value = "title", required = true) String title,
                             @RequestParam(value = "content", required = true) String content) {
        return entryService.createEntry(title, content, false);
    }

    @PostMapping("/{id}/select")
    public Entry selectEntry(@PathVariable Long id) {
        Entry entryToSelect = entryService.findById(id);
        entryToSelect.setSelected(!entryToSelect.getSelected());
        return entryService.editEntry(entryToSelect);
    }

    @PostMapping("/{id}/edit")
    public Entry editEntry(@PathVariable Long id,
                           @RequestParam(value = "title", required = true) String title,
                           @RequestParam(value = "content", required = true) String content) {
        Entry entryToEdit = entryService.findById(id);
        entryToEdit.setTitle(title);
        entryToEdit.setContent(content);
        return entryService.editEntry(entryToEdit);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteEntry(@PathVariable Long id) {
        entryService.deleteEntry(id);
    }
}
