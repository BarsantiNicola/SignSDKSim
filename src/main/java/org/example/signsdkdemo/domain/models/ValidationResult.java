package org.example.signsdkdemo.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResult {
    boolean isValid;
    boolean isPinLastTry;
    boolean isPinLocked;
}
