package edu.ntnu.idatt2105.g6.backend.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true)
public record MessageDTO(@NonNull Long conversationId,  @NonNull String username,
                         @NonNull String message, Timestamp timestamp) {

}
