package org.example.signsdkdemo.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("sessions")
public class Session {

    private String sessionToken;
    private String origin;
    private int    duration;

    @CreatedDate
    private Instant createdDate;
}
