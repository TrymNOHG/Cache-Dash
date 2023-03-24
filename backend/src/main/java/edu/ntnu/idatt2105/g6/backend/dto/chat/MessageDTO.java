package edu.ntnu.idatt2105.g6.backend.dto.chat;

import lombok.*;

public record MessageDTO(@NonNull Long conversationId,  @NonNull String username,
                         @NonNull String message) {

}
