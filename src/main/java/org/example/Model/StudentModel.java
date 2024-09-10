package org.example.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Validated
public class StudentModel implements Serializable {
    @Valid

    private int id;
    @Min(1)
    private int registerNumber;
    private String name;
    private String department;
    private boolean active;
    @JsonFormat(pattern="yyyy-MMM-dd hh:mm:a")
    private LocalDateTime createdTimeStamp;
    @JsonFormat(pattern="yyyy-MMM-dd hh:mm:a")
    private LocalDateTime updatedTimeStamp;
    public StudentModel(int id, int registerNumber, String name, String department, boolean active, LocalDateTime createdTimeStamp, LocalDateTime updatedTimeStamp) {
        this.id = id;
        this.registerNumber = registerNumber;
        this.name = name;
        this.department = department;
        this.active = active;
        this.createdTimeStamp=createdTimeStamp;
        this.updatedTimeStamp=updatedTimeStamp;
    }
}

