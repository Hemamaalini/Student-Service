package org.example.Handler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
@Data
public class ResponseClass {
    private int statusCode;
    private String error;
    private String message;
    //private String path;
    @JsonFormat(pattern="yyyy-MMM-dd hh:mm:a")
    private LocalDateTime timestamp;
    private List<?> data;

    public ResponseClass(int statusCode, String error, String message, LocalDateTime timestamp, List<?> data) {
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
       // this.path = path;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

}
