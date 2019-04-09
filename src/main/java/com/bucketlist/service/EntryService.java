package com.bucketlist.service;

import com.bucketlist.dao.EntryRepository;
import com.bucketlist.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntryService {

    @Autowired
    EntryRepository entryRepository;

    public List<Entry> getAllEntries() {
        List<Entry> entryList = new ArrayList<Entry>();
        entryRepository.findAll().forEach(entryList::add);
        return entryList;
    }

    public Entry findById(Long id) {
        return entryRepository.findById(id).get();
    }

    public Entry createEntry(String title, String content, Boolean selected) {
        return entryRepository.save(new Entry(title, content, selected));
    }

    public Entry editEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    public void deleteEntry(Long id) {
        entryRepository.deleteById(id);
    }
}
