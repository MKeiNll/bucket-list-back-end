package com.bucketlist.service;

import com.bucketlist.dao.EntryRepository;
import com.bucketlist.model.Entry;
import com.bucketlist.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntryService {

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    private ListUtils listUtils;

    public List<Entry> getAllEntries() {
        List<Entry> entryList = new ArrayList<Entry>();
        entryRepository.findAll().forEach(entryList::add);
        return entryList;
    }

    public Entry findById(Long id) {
        return entryRepository.findById(id).get();
    }

    public Entry createEntry(String title, String content, Boolean selected) {
        Long highestIndex = entryRepository.findFirstByOrderByIndexDesc().getIndex();
        return entryRepository.save(new Entry(title, content, selected, highestIndex + 1));
    }

    public List<Entry> moveEntry(Long from, Long to) {
        List<Entry> entryList = new ArrayList<Entry>();
        entryRepository.saveAll(listUtils.moveEntry(getAllEntries(), from, to)).forEach(entryList::add);
        return entryList;
    }

    public Entry editEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    public List<Entry> deleteEntry(Long id) {
        Long nonExistentIndex = findById(id).getIndex();
        List<Entry> entryList = new ArrayList<Entry>();
        entryRepository.deleteById(id);
        entryRepository.saveAll(listUtils.handleDeletion(getAllEntries(), nonExistentIndex)).forEach(entryList::add);
        return entryList;
    }
}
