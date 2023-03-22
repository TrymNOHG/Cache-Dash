package edu.ntnu.idatt2105.g6.backend.dto.listing;

import lombok.NonNull;
import org.springframework.lang.Nullable;

public record CategoryEditDTO(@NonNull Long userId, @NonNull String subCategory, @Nullable Long categoryId) {
}
