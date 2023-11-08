package pdr.chorumeblog.exceptions.core;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.List;

public record StandardError(
        @JsonFormat(shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
                timezone = "GMT")
        Instant timestamp,
        Integer status,
        List<String> error,
        String message,
        String path)
 {
}
