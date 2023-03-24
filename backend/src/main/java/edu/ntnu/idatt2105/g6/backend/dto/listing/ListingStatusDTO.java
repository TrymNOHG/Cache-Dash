package edu.ntnu.idatt2105.g6.backend.dto.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.ListingStatus;
import lombok.NonNull;

public record ListingStatusDTO(@NonNull Long userId, @NonNull Long itemId, @NonNull ListingStatus listingStatus) {
}
