package edu.ntnu.idatt2105.g6.backend.service.listing;

import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingUpdateDTO;

public interface IItemService {

    ListingDTO loadAllListings(String username);
    ListingDTO loadAllListings(Long itemId);
    void addListing(ListingDTO listing);
    void updateListing(ListingUpdateDTO listingUpdateDTO);
    void deleteListing(ListingDeletionDTO listingDeletionDTO);

}
