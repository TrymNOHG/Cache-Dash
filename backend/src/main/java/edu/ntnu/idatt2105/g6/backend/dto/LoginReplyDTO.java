package edu.ntnu.idatt2105.g6.backend.dto;

import lombok.NonNull;

public record LoginReplyDTO(@NonNull String username, @NonNull String token) {

}
