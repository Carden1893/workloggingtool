package com.kilian_beckenkamp.workloggingtool.worklogEntry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface WorklogEntryRepository extends JpaRepository<WorklogEntry, String> {

    @Query("SELECT DISTINCT wle.entryDate FROM WorklogEntry wle")
    List<LocalDateTime> getExistingEntryDays();

}
