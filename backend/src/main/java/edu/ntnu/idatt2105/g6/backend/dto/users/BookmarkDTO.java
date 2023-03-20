package edu.ntnu.idatt2105.g6.backend.dto.users;

import lombok.*;

public record BookmarkDTO(@NonNull Long itemId, @NonNull String username) {
}
