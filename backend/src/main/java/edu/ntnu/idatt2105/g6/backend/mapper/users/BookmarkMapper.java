package edu.ntnu.idatt2105.g6.backend.mapper.users;

import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkLoadDTO;
import edu.ntnu.idatt2105.g6.backend.mapper.listing.ListingMapper;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.users.Bookmark;
import edu.ntnu.idatt2105.g6.backend.model.users.BookmarkId;
import edu.ntnu.idatt2105.g6.backend.model.users.User;

import java.util.List;

/**
 The BookmarkMapper class is responsible for mapping Bookmark objects to BookmarkDTO objects and vice versa.
 */
public class BookmarkMapper {

    /**
     * Converts a list of Bookmark objects to a BookmarkLoadDTO object.
     *
     * @param bookmarkList The list of Bookmark objects to convert
     * @return The converted BookmarkLoadDTO object
     */
    public static BookmarkLoadDTO loadBookmarkDTO(List<Bookmark> bookmarkList) {
        return new BookmarkLoadDTO(bookmarkList
                .stream()
                .map(bookmark -> ListingMapper.toListing(bookmark.getItem()))
                .toList());
    }

    /**
     * Converts an Item object and a User object to a Bookmark object.
     *
     * @param item The Item object to convert
     * @param user The User object to convert
     * @return The converted Bookmark object
     */
    public static Bookmark toBookmark(Item item, User user){
        return Bookmark
                .builder()
                .id(new BookmarkId(item.getItemId(), user.getUserId()))
                .item(item)
                .user(user)
                .build();
    }

}
