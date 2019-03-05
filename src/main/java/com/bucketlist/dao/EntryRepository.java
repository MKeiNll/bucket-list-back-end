package com.bucketlist.dao;

import com.bucketlist.model.Entry;
import org.springframework.data.repository.CrudRepository;

public interface EntryRepository extends CrudRepository<Entry, Long> {
}
