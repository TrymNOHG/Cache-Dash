package edu.ntnu.idatt2105.g6.backend.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
public record BookmarkDTO(@NonNull Long itemId, @NonNull String username) {
}
