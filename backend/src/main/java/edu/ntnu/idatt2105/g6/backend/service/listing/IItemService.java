package edu.ntnu.idatt2105.g6.backend.service.listing;

import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingUpdateDTO;

import java.util.List;

public interface IItemService {

    ListingDTO loadListing(Long itemId);
    List<ListingDTO> loadAllListings(String username);
    void addListing(ListingDTO listing);
    void updateListing(ListingUpdateDTO listingUpdateDTO);
    void deleteListing(ListingDeletionDTO listingDeletionDTO);

}
