package com.bucketlist.controller;

import com.bucketlist.model.Entry;
import com.bucketlist.service.EntryService;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:9000")
public class EntryController {

    @Autowired
    EntryService entryService;
    private JSONObject jsonSubject;
    private JSONObject jsonSchema;

    @RequestMapping(method = RequestMethod.GET)
    public List<Entry> getAllEntries() {
        return entryService.getAllEntries();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Entry createEntry(@RequestParam(value = "title", required = true) String title,
                             @RequestParam(value = "content", required = true) String content,
                             @RequestParam(value = "selected", required = true) Boolean selected) {
        return entryService.createEntry(title, content, selected);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Entry editEntry(@RequestBody(required = true) Entry entry) {
        JSONObject jsonSchema = null;
        try {
            jsonSchema = new JSONObject(
                    new JSONTokener(new ClassPathResource("entry_schema.json").getInputStream()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(new JSONObject(entry));

        return entryService.editEntry(entry);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteEntry(@PathVariable Long id) {
        entryService.deleteEntry(id);
    }
}
