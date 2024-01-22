package org.example.signsdkdemo.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {
    String token;
    int duration;
}
