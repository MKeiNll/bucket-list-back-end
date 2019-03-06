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

    public Entry createEntry(String title, String content) {
        return entryRepository.save(new Entry(title, content));
    }

    public void deleteEntry(Long id) {
        entryRepository.deleteById(id);
    }
}
