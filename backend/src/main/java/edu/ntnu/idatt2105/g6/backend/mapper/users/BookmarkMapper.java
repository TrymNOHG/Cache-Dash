package edu.ntnu.idatt2105.g6.backend.mapper.users;

import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkLoadDTO;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.users.Bookmark;
import edu.ntnu.idatt2105.g6.backend.model.users.User;

import java.util.List;

public class BookmarkMapper {

    public static BookmarkLoadDTO loadBookmarkDTO(List<Bookmark> bookmarkList) {
        return new BookmarkLoadDTO(bookmarkList
                .stream()
                .map(Bookmark::getItem)
                .toList());
    }

    public static Bookmark toBookmark(Item item, User user){
        return Bookmark
                .builder()
                .item(item)
                .user(user)
                .build();
    }



}
