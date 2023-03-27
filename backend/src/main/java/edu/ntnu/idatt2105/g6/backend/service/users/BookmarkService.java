package edu.ntnu.idatt2105.g6.backend.service.users;

import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.exception.UnauthorizedException;
import edu.ntnu.idatt2105.g6.backend.exception.exists.BookmarkExistsException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.BookmarkNotFoundException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.ItemNotFoundException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.UserNotFoundException;
import edu.ntnu.idatt2105.g6.backend.mapper.users.BookmarkMapper;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.users.Bookmark;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.listing.ItemRepository;
import edu.ntnu.idatt2105.g6.backend.repo.users.BookmarkRepository;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 This service class handles the business logic for bookmark-related operations.
 It implements the IBookmarkService interface.

 */
@Service
@RequiredArgsConstructor
public class BookmarkService implements IBookmarkService{

    private final BookmarkRepository bookmarkRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(BookmarkService.class);

    /**
     * Adds a bookmark to an item for a user.
     *
     * @param bookmarkDTO A DTO containing the itemId and username for the bookmark.
     * @throws ItemNotFoundException Thrown when the item is not found in the database.
     * @throws UserNotFoundException Thrown when the user is not found in the database.
     */
    @Transactional
    @Override
    public void addBookmark(BookmarkDTO bookmarkDTO) {
        Item item = itemRepository.findByItemId(bookmarkDTO.itemId()).orElseThrow(() -> new ItemNotFoundException(bookmarkDTO.itemId()));
        User user = userRepository.findByUsername(bookmarkDTO.username()).orElseThrow(() -> new UserNotFoundException(bookmarkDTO.username()));

        logger.info("Checking if bookmark already exists");
        if(bookmarkRepository.findByItemAndUser(item, user).isPresent()) throw new BookmarkExistsException();

        Bookmark bookmark = BookmarkMapper.toBookmark(item, user);
        logger.info("Saving bookmark: " + bookmark);

        bookmarkRepository.save(bookmark);
    }

    /**
     * Deletes a bookmark for a specific item and user.
     *
     * @param bookmarkDeletionDTO A DTO containing the itemId, actingUser, and username for the bookmark.
     * @throws BookmarkNotFoundException Thrown when the bookmark is not found in the database.
     * @throws ItemNotFoundException Thrown when the item is not found in the database.
     * @throws UserNotFoundException Thrown when the user is not found in the database.
     * @throws UnauthorizedException Thrown when the acting user is not authorized to delete the bookmark.
     */
    @Transactional
    @Override
    public void deleteBookmark(BookmarkDeletionDTO bookmarkDeletionDTO) {
        User actingUser = userRepository.findByUsername(bookmarkDeletionDTO.actingUser()).orElseThrow(() -> new UserNotFoundException(bookmarkDeletionDTO.actingUser()));
        User user = userRepository.findByUsername(bookmarkDeletionDTO.username()).orElseThrow(() -> new UserNotFoundException(bookmarkDeletionDTO.username()));

        if (actingUser.getRole() == Role.ADMIN || actingUser.getUsername().equals(user.getUsername())) {
            Item item = itemRepository.findByItemId(bookmarkDeletionDTO.itemId()).orElseThrow(() -> new ItemNotFoundException(bookmarkDeletionDTO.itemId()));
            Bookmark bookmark = bookmarkRepository.findByItemAndUser(item, user).orElseThrow(() -> new BookmarkNotFoundException(item.getBriefDesc()));
            bookmarkRepository.delete(bookmark);
        }
        else throw new UnauthorizedException(actingUser.getUsername());
    }

    /**
     * Deletes all bookmarks for a given user.
     *
     * @param userDeletionDTO DTO containing the username of the user requesting the deletion and the username of the user whose bookmarks are to be deleted.
     * @throws UnauthorizedException if the acting user is not an admin and is not deleting their own bookmarks.
     * @throws UserNotFoundException if either the acting user or the user to be deleted is not found.
     * @throws BookmarkNotFoundException if no bookmarks are found for the given user.
     */
    @Transactional
    @Override
    public void deleteAllBookmarks(UserDeletionDTO userDeletionDTO) {
        User actingUser = userRepository.findByUsername(userDeletionDTO.username()).orElseThrow(() -> new UserNotFoundException(userDeletionDTO.username()));
        User user = userRepository.findByUsername(userDeletionDTO.userToDelete()).orElseThrow(() -> new UserNotFoundException(userDeletionDTO.userToDelete()));

        if (actingUser.getRole() == Role.ADMIN || actingUser.getUsername().equals(user.getUsername())) {
            bookmarkRepository.deleteAll(bookmarkRepository.findAllByUser(user).orElseThrow(() -> new BookmarkNotFoundException(userDeletionDTO.userToDelete())));
        }
        else throw new UnauthorizedException(actingUser.getUsername());
    }

    /**
     * Loads all bookmarks for a given user.
     *
     * @param username String containing the username of the user requesting the bookmarks and the username of the user whose bookmarks are to be loaded.
     * @return a DTO containing a list of bookmarked items for the given user.
     * @throws UnauthorizedException if the acting user is not an admin and is not loading their own bookmarks.
     * @throws UserNotFoundException if either the acting user or the user to be loaded is not found.
     * @throws BookmarkNotFoundException if no bookmarks are found for the given user.
     */
    @Override
    public BookmarkLoadDTO loadBookmarks(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        List<Bookmark> bookmarkList = bookmarkRepository.findAllByUser(user).orElseThrow(() -> new BookmarkNotFoundException(username));
        return BookmarkMapper.loadBookmarkDTO(bookmarkList);
    }
}
