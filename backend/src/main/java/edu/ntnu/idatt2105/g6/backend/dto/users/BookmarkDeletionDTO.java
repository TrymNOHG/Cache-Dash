package edu.ntnu.idatt2105.g6.backend.dto.users;

import lombok.NonNull;

public record BookmarkDeletionDTO(@NonNull String actingUser, @NonNull String username, @NonNull Long itemId) {
}
