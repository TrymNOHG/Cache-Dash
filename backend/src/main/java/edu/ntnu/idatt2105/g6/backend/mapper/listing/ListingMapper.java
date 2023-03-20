package edu.ntnu.idatt2105.g6.backend.mapper.listing;


import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.users.User;

public class ListingMapper {

    public Item toItem(User user, Category category, ListingDTO listingDTO){
        Item item = Item
                .builder()
                .user(user)
                .briefDesc(listingDTO.getBriefDesc())
                .fullDesc(listingDTO.getFullDesc())
                .address(listingDTO.getAddress())
                .county(listingDTO.getCounty())
                .category(category)
                .price(listingDTO.getPrice())
                .thumbnail(listingDTO.getThumbnail())
                .keyInfoList(listingDTO.getKeyInfoList())
                .build();
        return item;
    }


}
