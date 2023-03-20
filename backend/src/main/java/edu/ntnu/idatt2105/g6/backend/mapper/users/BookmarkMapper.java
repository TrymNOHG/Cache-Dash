package edu.ntnu.idatt2105.g6.backend.mapper.users;

import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkLoadDTO;
import edu.ntnu.idatt2105.g6.backend.model.users.Bookmark;

import java.util.List;

public class BookmarkMapper {

    public BookmarkLoadDTO loadBookmarkDTO(List<Bookmark> bookmarkList) {
        return new BookmarkLoadDTO(bookmarkList
                .stream()
                .map(Bookmark::getItem)
                .toList());
    }



}
