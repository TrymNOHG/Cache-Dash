package edu.ntnu.idatt2105.g6.backend.service.users;

import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserDeletionDTO;

public interface IBookmarkService {

    void addBookmark(BookmarkDTO bookmarkDTO);
    void deleteBookmark(BookmarkDeletionDTO bookmarkDeletionDTO);
    void deleteAllBookmarks(UserDeletionDTO userDeletionDTO);
    BookmarkLoadDTO loadBookmarks(String username);


}
