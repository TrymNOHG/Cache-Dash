package edu.ntnu.idatt2105.g6.backend.dto.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import lombok.*;

@Builder
public record CategoryDTO(@NonNull Category rootCategory){
}
