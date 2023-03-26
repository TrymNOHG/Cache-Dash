package edu.ntnu.idatt2105.g6.backend.mapper.listing;


import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingLoadDTO;
import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.listing.ListingStatus;
import edu.ntnu.idatt2105.g6.backend.model.users.User;

/**
 This class provides mapping methods to map between Listing DTOs and Listing entities.
 */
public class ListingMapper {

    /**
     * Map ListingDTO to an Item entity.
     *
     * @param user the user creating the item
     * @param category the category of the item
     * @param listingDTO the DTO containing the information to be mapped
     * @return an Item entity with the mapped information
     */
    public static Item toItem(User user, Category category, ListingDTO listingDTO){
        Item item = Item
                .builder()
                .user(user)
                .briefDesc(listingDTO.getBriefDesc())
                .fullDesc(listingDTO.getFullDesc())
                .address(listingDTO.getAddress())
                .county(listingDTO.getCounty())
                .category(category)
                .price(listingDTO.getPrice())
                .status(ListingStatus.ACTIVE)
                .thumbnail(listingDTO.getThumbnail())
                .keyInfoList(listingDTO.getKeyInfoList())
                .build();
        return item;
    }

    /**
     * Map an Item entity to a ListingLoadDTO.
     *
     * @param item the Item entity to be mapped
     * @return a ListingLoadDTO with the mapped information
     */
    public static ListingLoadDTO toListing(Item item) {
        return ListingLoadDTO
                .builder()
                .itemId(item.getItemId())
                .username(item.getUser().getUsername())
                .briefDesc(item.getBriefDesc())
                .fullDesc(item.getFullDesc())
                .address(item.getAddress())
                .county(item.getCounty())
                .categoryId(item.getCategory().getCategoryId())
                .price(item.getPrice())
                .listingStatus(item.getStatus())
                .thumbnail(item.getThumbnail())
                .keyInfoList(item.getKeyInfoList())
                .build();
    }


}
