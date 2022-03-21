package com.kilian_beckenkamp.workloggingtool.worklogEntry;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "worklogEntry")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorklogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String workDescription;

    private LocalDateTime entryDate;


}