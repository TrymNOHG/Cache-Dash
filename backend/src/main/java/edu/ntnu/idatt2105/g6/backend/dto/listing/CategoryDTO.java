package edu.ntnu.idatt2105.g6.backend.dto.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.List;

@Builder
public record CategoryDTO(@NonNull Long categoryId, @NonNull String categoryName, @Nullable List<CategoryDTO> subCategories){
}
