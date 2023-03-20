package edu.ntnu.idatt2105.g6.backend.dto;

import lombok.*;

import java.sql.Timestamp;

public record MessageDTO(@NonNull Long conversationId,  @NonNull String username,
                         @NonNull String message, Timestamp timestamp) {

}
