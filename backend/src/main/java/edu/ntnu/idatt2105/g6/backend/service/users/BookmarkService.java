package edu.ntnu.idatt2105.g6.backend.service.users;

import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.exception.UnauthorizedException;
import edu.ntnu.idatt2105.g6.backend.mapper.users.BookmarkMapper;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.users.Bookmark;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.listing.ItemRepository;
import edu.ntnu.idatt2105.g6.backend.repo.users.BookmarkRepository;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService implements IBookmarkService{

    private final BookmarkRepository bookmarkRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public void addBookmark(BookmarkDTO bookmarkDTO) {
        Item item = itemRepository.findByItemId(bookmarkDTO.itemId()).orElseThrow();
        User user = userRepository.findByUsername(bookmarkDTO.username()).orElseThrow();
        Bookmark bookmark = BookmarkMapper.toBookmark(item, user);
        bookmarkRepository.save(bookmark);
    }

    @Override
    public void deleteBookmark(BookmarkDeletionDTO bookmarkDeletionDTO) {
        User actingUser = userRepository.findByUsername(bookmarkDeletionDTO.actingUser()).orElseThrow();
        User user = userRepository.findByUsername(bookmarkDeletionDTO.username()).orElseThrow();

        if (actingUser.getRole() == Role.ADMIN || actingUser.getUsername().equals(user.getUsername())) {
            Item item = itemRepository.findByItemId(bookmarkDeletionDTO.itemId()).orElseThrow();
            Bookmark bookmark = bookmarkRepository.findByItemAndUser(item, user).orElseThrow();
            bookmarkRepository.delete(bookmark);
        }
        else throw new UnauthorizedException(actingUser.getUsername());
    }

    @Override
    public void deleteAllBookmarks(UserDeletionDTO userDeletionDTO) {
        User actingUser = userRepository.findByUsername(userDeletionDTO.username()).orElseThrow();
        User user = userRepository.findByUsername(userDeletionDTO.userToDelete()).orElseThrow();

        if (actingUser.getRole() == Role.ADMIN || actingUser.getUsername().equals(user.getUsername())) {
            bookmarkRepository.deleteAll(bookmarkRepository.findAllByUser(user).orElseThrow());
        }
        else throw new UnauthorizedException(actingUser.getUsername());
    }

    @Override
    public BookmarkLoadDTO loadBookmarks(UserDeletionDTO userDeletionDTO) {
        User actingUser = userRepository.findByUsername(userDeletionDTO.username()).orElseThrow();
        User user = userRepository.findByUsername(userDeletionDTO.userToDelete()).orElseThrow();

        if (actingUser.getRole() == Role.ADMIN || actingUser.getUsername().equals(user.getUsername())) {
            List<Bookmark> bookmarkList = bookmarkRepository.findAllByUser(user).orElseThrow();
            BookmarkLoadDTO bookmarkLoadDTO = BookmarkMapper.loadBookmarkDTO(bookmarkList);
            return bookmarkLoadDTO;
        }
        else throw new UnauthorizedException(actingUser.getUsername());
    }
}
