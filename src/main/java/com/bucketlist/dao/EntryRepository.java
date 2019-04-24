package com.bucketlist.dao;

import com.bucketlist.model.Entry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {
    Entry findFirstByOrderByIndexDesc();
}
