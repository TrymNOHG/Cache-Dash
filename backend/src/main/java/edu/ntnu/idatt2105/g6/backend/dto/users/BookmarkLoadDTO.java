package edu.ntnu.idatt2105.g6.backend.dto.users;

import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingLoadDTO;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.users.Bookmark;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

public record BookmarkLoadDTO(@NonNull List<ListingLoadDTO> bookmarkedItems) {
}
