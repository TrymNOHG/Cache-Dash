package edu.ntnu.idatt2105.g6.backend.dto.users;

import lombok.NonNull;

public record LoginReplyDTO(@NonNull String username, @NonNull String token) {

}
