package com.example.GReads_Clone.ReadingLists;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReadingListEntryRepository extends JpaRepository<ReadingListEntry,Long> {


}
