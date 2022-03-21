package com.kilian_beckenkamp.workloggingtool.worklogEntry;

import net.bytebuddy.asm.Advice;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorklogEntryService {

    private final WorklogEntryRepository worklogEntryRepository;

    @Autowired
    public WorklogEntryService(WorklogEntryRepository worklogEntryRepository) {
        this.worklogEntryRepository = worklogEntryRepository;
    }

    public void saveNewWorklogEntry(WorklogEntry wle) {
        worklogEntryRepository.saveAndFlush(wle);
    }

    public List<WorklogEntry> getWorklogEntries() {
        List<WorklogEntry> logEntries = new ArrayList<>();
        logEntries = worklogEntryRepository.findAll();
        return logEntries;
    }

    public List<WorklogEntry> getWorklogEntriesForSpecificDay(String dateString) {
        List<WorklogEntry> worklogEntries = getWorklogEntries();
        List<WorklogEntry> collectedEntries = worklogEntries.stream().filter(worklogEntry -> {
            String entryDate =
                            String.valueOf(worklogEntry.getEntryDate().getDayOfMonth() < 10 ?
                                    "0" + worklogEntry.getEntryDate().getDayOfMonth() :
                                    worklogEntry.getEntryDate().getDayOfMonth()) + "." +
                            String.valueOf(worklogEntry.getEntryDate().getMonthValue() < 10 ?
                                    "0" + worklogEntry.getEntryDate().getMonthValue() :
                                    worklogEntry.getEntryDate().getMonthValue()) + "." +
                            String.valueOf(worklogEntry.getEntryDate().getYear());
            return entryDate.equals(dateString);
        }).collect(Collectors.toList());
        return collectedEntries;
    }


    public List<String> getExistingEntrydays() {
        List<LocalDateTime> existingEntryDays = worklogEntryRepository.getExistingEntryDays();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        List<String> dateStrings = new ArrayList<>();
        existingEntryDays.stream().forEach(localDateTime ->
                        dateStrings.add(formatter.format(localDateTime)));
        return removeDuplicates(dateStrings);
    }


    private List<String> removeDuplicates(List<String> listWithDuplicates) {
        List<String> listWithoutDuplicates = new ArrayList<>();
        for (String listEntry : listWithDuplicates) {
            if (!listWithoutDuplicates.contains(listEntry))
                listWithoutDuplicates.add(listEntry);
        }
        return listWithoutDuplicates;
    }
}
