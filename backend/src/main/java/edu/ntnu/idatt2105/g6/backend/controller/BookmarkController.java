package edu.ntnu.idatt2105.g6.backend.controller;

import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.service.listing.ItemService;
import edu.ntnu.idatt2105.g6.backend.service.users.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/save")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> save(@RequestBody BookmarkDTO bookmark) {
        bookmarkService.addBookmark(bookmark);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> delete(@RequestBody BookmarkDeletionDTO bookmark) {
        bookmarkService.deleteBookmark(bookmark);
        return ResponseEntity.ok().build();
    }

    //TODO: fix DTO for loadBookmarks why UserDeletionDTO
    @PostMapping("/load")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> loadAllBookmarks(@RequestBody UserDeletionDTO user) {
        BookmarkLoadDTO bookmarks = bookmarkService.loadBookmarks(user);
        return ResponseEntity.ok(bookmarks);
    }

}
