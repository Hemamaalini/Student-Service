package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@Entity
@Table(name = "student_Service")
@NoArgsConstructor
public class StudentEntity {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MMM-dd'T'hh:mma");

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private int registerNumber;

    @Column
    private String department;

    @Column
    private boolean active;

    @Column
    @JsonFormat(pattern="yyyy-MMM-dd hh:mm:a")
    private LocalDateTime createdTimeStamp;

    @Column
    @JsonFormat(pattern="yyyy-MMM-dd hh:mm:a")
    private LocalDateTime updatedTimeStamp;


// @Temporal(TemporalType.TIMESTAMP)
   /* @Column(nullable = false, updatable = false)
    private LocalDateTime currentTime = LocalDateTime.now();*/
}

