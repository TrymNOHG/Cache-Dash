package edu.ntnu.idatt2105.g6.backend.controller;

import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationRequest;
import edu.ntnu.idatt2105.g6.backend.service.listing.ItemService;
import edu.ntnu.idatt2105.g6.backend.service.users.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/user/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final Logger logger = LoggerFactory.getLogger(BookmarkController.class);

    @PostMapping("/save")
    @Operation(summary = "Save a item to bookmarks")
    public ResponseEntity<Object> save(@ParameterObject @RequestBody BookmarkDTO bookmark) {
        logger.info(bookmark.username() + " wants to save " + bookmark.itemId() + " in their bookmarks!");
        bookmarkService.addBookmark(bookmark);
        logger.info("Bookmark saved!");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    @Operation(summary = "Delete a item from bookmarks")
    public ResponseEntity<Object> delete(@ParameterObject @RequestBody BookmarkDeletionDTO bookmark) {
        logger.info("Deleting bookmarks");
        bookmarkService.deleteBookmark(bookmark);
        logger.info("Bookmark deleted");
        return ResponseEntity.ok().build();
    }

    //TODO: fix DTO for loadBookmarks why UserDeletionDTO
    @GetMapping("/load")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading bookmarks",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookmarkLoadDTO.class)) })}
    )
    @Operation(summary = "Load all bookmarks")
    public ResponseEntity<BookmarkLoadDTO> loadAllBookmarks(Authentication authentication) {
        logger.info("Loading all bookmarks from " + authentication.getName());
        BookmarkLoadDTO bookmarks = bookmarkService.loadBookmarks(authentication.getName());
        logger.info("Loaded all bookmarks from " + authentication.getName());
        return ResponseEntity.ok(bookmarks);
    }

}
