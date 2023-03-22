package edu.ntnu.idatt2105.g6.backend.mapper.listing;


import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.users.User;

public class ListingMapper {

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
                .thumbnail(listingDTO.getThumbnail())
                .keyInfoList(listingDTO.getKeyInfoList())
                .build();
        return item;
    }

    public static ListingDTO toListing(Item item) {
        return ListingDTO
                .builder()
                .itemId(item.getItemId())
                .username(item.getUser().getUsername())
                .briefDesc(item.getBriefDesc())
                .fullDesc(item.getFullDesc())
                .address(item.getAddress())
                .county(item.getCounty())
                .categoryId(item.getCategory().getCategoryId())
                .price(item.getPrice())
                .thumbnail(item.getThumbnail())
                .keyInfoList(item.getKeyInfoList())
                .build();
    }


}
