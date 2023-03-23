package edu.ntnu.idatt2105.g6.backend.model.users;

import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.listing.ListingStatus;
import edu.ntnu.idatt2105.g6.backend.model.users.Bookmark;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookmarkTest {

    @Nested
    class Null_columns_constructors {

        @Test
        void item_id_cannot_be_null(){
            //TODO does not throw error for empty constructor
            assertThrows(NullPointerException.class, () -> {
                Bookmark bookmark = new Bookmark(null, null, new User("Ole123", "123", "Ole Norman", "Ole@gmail.com", Role.USER));
            });
        }

        @Test
        void user_id_cannot_be_null(){
            //TODO does not throw error for empty constructor
            assertThrows(NullPointerException.class, () -> {
                Bookmark bookmark = new Bookmark(new BookmarkId(), new Item(new User("Ole123", "123", "Ole Norman", "Ole@gmail.com", Role.USER), "test", "Nordkapp", "Troms og Finnmark", Category.builder().build(), 200000, ListingStatus.ACTIVE), null);
            });
        }

    }

    @Nested
    class Null_variables{

        @Test
        void item_id_cannot_be_set_to_null(){
            Bookmark bookmark = new Bookmark(new BookmarkId(),new Item(new User("Ole123", "123", "Ole Norman", "Ole@gmail.com", Role.USER), "test", "Nordkapp", "Troms og Finnmark", new Category(), 200000, ListingStatus.ACTIVE), new User("Ole123", "123", "Ole Norman", "Ole@gmail.com", Role.USER));
            assertThrows(NullPointerException.class, () -> {
                bookmark.setUser(null);
            });
        }

        @Test
        void user_id_cannot_be_set_to_null(){
            Bookmark bookmark = new Bookmark(new BookmarkId() ,new Item(new User("Ole123", "123", "Ole Norman", "Ole@gmail.com", Role.USER), "test", "Nordkapp", "Troms og Finnmark", new Category(), 200000, ListingStatus.ACTIVE), new User("Ole123", "123", "Ole Norman", "Ole@gmail.com", Role.USER));
            assertThrows(NullPointerException.class, () -> {
                bookmark.setItem(null);
            });
        }
    }

    @Nested
    class Getters{

        @Test
        void item_id_getter_returns_correct_value(){

            User user = new User("Ole123", "123", "Ole Norman", "Ole@gmail.com", Role.USER);
            Item item = new Item(user, "test", "Nordkapp", "Troms og Finnmark", new Category(), 200000, ListingStatus.ACTIVE);
            Bookmark bookmark = new Bookmark(new BookmarkId(), item, user);
            assertEquals(item, bookmark.getItem());
        }

        @Test
        void user_id_getter_returns_correct_value(){
            User user = new User("Ole123", "123", "Ole Norman", "Ole@gmail.com", Role.USER);
            Item item = new Item(user, "test", "Nordkapp", "Troms og Finnmark", new Category(), 200000, ListingStatus.ACTIVE);
            Bookmark bookmark = new Bookmark(new BookmarkId(),item, user);
            assertEquals(user, bookmark.getUser());
        }
    }


}