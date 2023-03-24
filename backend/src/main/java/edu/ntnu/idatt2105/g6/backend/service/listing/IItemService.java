package edu.ntnu.idatt2105.g6.backend.service.listing;

import edu.ntnu.idatt2105.g6.backend.dto.listing.*;

import java.util.List;

public interface IItemService {

    ListingLoadDTO loadListing(Long itemId);
    List<ListingLoadDTO> loadAllListings(String username);
    List<ListingLoadDTO> loadArchive(Long userId);
    void addListing(ListingDTO listing);
    void updateListing(ListingUpdateDTO listingUpdateDTO);
    void sellListing(ListingStatusDTO listingStatusDTO);
    void deleteListing(ListingDeletionDTO listingDeletionDTO);

}
