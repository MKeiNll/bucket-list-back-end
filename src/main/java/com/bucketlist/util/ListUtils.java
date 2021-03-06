package com.bucketlist.util;

import com.bucketlist.model.Entry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListUtils {

    public List<Entry> moveEntry(List<Entry> entries, Long from, Long to) {
        Long rowsAffected = from - to;
        boolean up = rowsAffected > 0;
        List<Long> indicesToAffect = new ArrayList<>();
        if (!up) {
            for (long i = from + 1; i <= to; i++) {
                indicesToAffect.add(i);
            }
        } else if (up) {
            for (long i = from - 1; i >= to; i--) {
                indicesToAffect.add(i);
            }
        }

        for (Entry entry : entries) {
            Long entryIndex = entry.getIndex();
            if (entryIndex == from) {
                entry.setIndex(to);
            } else if (indicesToAffect.contains(entryIndex)) {
                if (!up) {
                    entry.setIndex(entryIndex - 1);
                } else if (up) {
                    entry.setIndex(entryIndex + 1);
                }
            }
        }
        return entries;
    }

    public List<Entry> handleDeletion(List<Entry> entries, Long nonExistentIndex) {
        for (Entry entry : entries) {
            Long entryIndex = entry.getIndex();
            if (entryIndex > nonExistentIndex) {
                entry.setIndex(entryIndex - 1);
            }
        }
        return entries;
    }
}
