package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.BookmarkLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.exception.UnauthorizedException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.ItemNotFoundException;
import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.listing.ListingStatus;
import edu.ntnu.idatt2105.g6.backend.model.users.Bookmark;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.listing.CategoryRepository;
import edu.ntnu.idatt2105.g6.backend.repo.listing.ItemRepository;
import edu.ntnu.idatt2105.g6.backend.repo.users.BookmarkRepository;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import edu.ntnu.idatt2105.g6.backend.service.users.BookmarkService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
public class BookmarkServiceIntegrationTest {



    @Nested
    @SpringBootTest
    class AddBookmark{

        @Autowired
        BookmarkService bookmarkService;

        @Autowired
        BookmarkRepository bookmarkRepository;

        @Autowired
        ItemRepository itemRepository;

        @Autowired
        UserRepository userRepository;

        @Test
        public void addBookmark_adds_correct_bookmark(){
            User user = User
                    .builder()
                    .username("Test")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.USER)
                    .build();

            Category category = Category
                    .builder()
                    .subCategory("Mercedes")
                    .mainCategory(null)
                    .build();

            Item item = Item
                    .builder()
                    .user(user)
                    .briefDesc("info")
                    .address("Nordkapp")
                    .county("Troms og Finnmark")
                    .category(category)
                    .price(100)
                    .status(ListingStatus.ACTIVE)
                    .build();

            userRepository.save(user);
            itemRepository.save(item);

            BookmarkDTO bookmarkDTO = new BookmarkDTO(1L, "Test");

            bookmarkService.addBookmark(bookmarkDTO);

            assertDoesNotThrow(() -> {
                bookmarkRepository.findByItemAndUser(item, user).orElseThrow();
            });


        }

        @Test
        public void addBookmarks_throws_ItemNotFoundException_for_non_existing_item(){
            BookmarkDTO bookmarkDTO = new BookmarkDTO(1L, "Test");

            assertThrows(ItemNotFoundException.class, () -> {
                bookmarkService.addBookmark(bookmarkDTO);

            });
        }

    }


    @Nested
    @SpringBootTest
    class DeleteBookmark{
        @Autowired
        BookmarkService bookmarkService;

        @Autowired
        BookmarkRepository bookmarkRepository;

        @Autowired
        ItemRepository itemRepository;

        @Autowired
        UserRepository userRepository;

        @Test
        public void deleteBookmark_deletes_bookmark(){
            User user = User
                    .builder()
                    .username("Test")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.USER)
                    .build();

            Category category = Category
                    .builder()
                    .subCategory("Mercedes")
                    .mainCategory(null)
                    .build();

            Item item = Item
                    .builder()
                    .user(user)
                    .briefDesc("info")
                    .address("Nordkapp")
                    .county("Troms og Finnmark")
                    .category(category)
                    .price(100)
                    .status(ListingStatus.ACTIVE)
                    .build();;

            userRepository.save(user);
            itemRepository.save(item);

            BookmarkDTO bookmarkDTO = new BookmarkDTO(1L, "Test");

            bookmarkService.addBookmark(bookmarkDTO);

            BookmarkDeletionDTO bookmarkDeletionDTO = new BookmarkDeletionDTO("Test", "Test", 1L);

            assertDoesNotThrow(() -> {
                bookmarkService.deleteBookmark(bookmarkDeletionDTO);
            });

        }

        @Test
        public void deleteBookmark_throws_UnauthorizedException(){
            User user = User
                    .builder()
                    .username("Test")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.USER)
                    .build();

            User user2 = User
                    .builder()
                    .username("NotTest")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.USER)
                    .build();

            Category category = Category
                    .builder()
                    .subCategory("Mercedes")
                    .mainCategory(null)
                    .build();

            Item item = Item
                    .builder()
                    .user(user)
                    .briefDesc("info")
                    .address("Nordkapp")
                    .county("Troms og Finnmark")
                    .category(category)
                    .price(100)
                    .status(ListingStatus.ACTIVE)
                    .build();;

            userRepository.save(user);
            itemRepository.save(item);
            userRepository.save(user2);

            BookmarkDTO bookmarkDTO = new BookmarkDTO(1L, "Test");

            bookmarkService.addBookmark(bookmarkDTO);

            BookmarkDeletionDTO bookmarkDeletionDTO = new BookmarkDeletionDTO("Test", "NotTest", 1L);

            assertThrows(UnauthorizedException.class, () ->{
                bookmarkService.deleteBookmark(bookmarkDeletionDTO);
            });

        }

    }

    @Nested
    @SpringBootTest
    class DeleteAllBookmarks{
        @Autowired
        BookmarkService bookmarkService;

        @Autowired
        BookmarkRepository bookmarkRepository;

        @Autowired
        ItemRepository itemRepository;

        @Autowired
        UserRepository userRepository;

        @Test
        public void deleteBookmark_deletes_bookmark(){
            User user = User
                    .builder()
                    .username("Test")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.USER)
                    .build();

            Category category = Category
                    .builder()
                    .subCategory("Mercedes")
                    .mainCategory(null)
                    .build();

            Category category2 = Category
                    .builder()
                    .subCategory("Ford")
                    .mainCategory(null)
                    .build();

            Item item = Item
                    .builder()
                    .user(user)
                    .briefDesc("info")
                    .address("Nordkapp")
                    .county("Troms og Finnmark")
                    .category(category)
                    .price(100)
                    .status(ListingStatus.ACTIVE)
                    .build();

            Item item2 = Item
                    .builder()
                    .user(user)
                    .briefDesc("info2")
                    .address("Nordkapp")
                    .county("Troms og Finnmark")
                    .category(category2)
                    .price(1000)
                    .status(ListingStatus.ACTIVE)
                    .build();

            userRepository.save(user);
            itemRepository.save(item);
            itemRepository.save(item2);

            BookmarkDTO bookmarkDTO = new BookmarkDTO(1L, "Test");
            BookmarkDTO bookmarkDTO2 = new BookmarkDTO(2L, "Test");


            bookmarkService.addBookmark(bookmarkDTO);
            bookmarkService.addBookmark(bookmarkDTO2);

            UserDeletionDTO userDeletionDTO = new UserDeletionDTO("Test", "Test");
            assertDoesNotThrow(() -> {
                bookmarkService.deleteAllBookmarks(userDeletionDTO);
            });

        }


    }

    @Nested
    @SpringBootTest
    class LoadBookmarks{
        @Autowired
        BookmarkService bookmarkService;

        @Autowired
        BookmarkRepository bookmarkRepository;

        @Autowired
        ItemRepository itemRepository;

        @Autowired
        UserRepository userRepository;

        @Test
        public void loadBookmarks_loads_all_bookmarks_for_user(){
            User user = User
                    .builder()
                    .username("Test")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.USER)
                    .build();

            Category category = Category
                    .builder()
                    .subCategory("Mercedes")
                    .mainCategory(null)
                    .build();

            Category category2 = Category
                    .builder()
                    .subCategory("Ford")
                    .mainCategory(null)
                    .build();

            Item item = Item
                    .builder()
                    .user(user)
                    .briefDesc("info")
                    .address("Nordkapp")
                    .county("Troms og Finnmark")
                    .category(category)
                    .price(100)
                    .status(ListingStatus.ACTIVE)
                    .build();

            Item item2 = Item
                    .builder()
                    .user(user)
                    .briefDesc("info2")
                    .address("Nordkapp")
                    .county("Troms og Finnmark")
                    .category(category2)
                    .price(1000)
                    .status(ListingStatus.ACTIVE)
                    .build();

            userRepository.save(user);
            itemRepository.save(item);
            itemRepository.save(item2);

            BookmarkDTO bookmarkDTO = new BookmarkDTO(1L, "Test");
            BookmarkDTO bookmarkDTO2 = new BookmarkDTO(2L, "Test");


            bookmarkService.addBookmark(bookmarkDTO);
            bookmarkService.addBookmark(bookmarkDTO2);
            UserDeletionDTO userDeletionDTO = new UserDeletionDTO("Test", "Test");

            BookmarkLoadDTO bookmarkLoadDTO = bookmarkService.loadBookmarks(userDeletionDTO.username());

            assertEquals(2, bookmarkLoadDTO.bookmarkedItems().size());

        }


    }

}
