package edu.ntnu.idatt2105.g6.backend.model;

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
                Bookmark bookmark = new Bookmark(null, new User("Ole123", "123", "Ole Norman", "Ole@gmail.com", Role.USER));
            });
        }

        @Test
        void user_id_cannot_be_null(){
            //TODO does not throw error for empty constructor
            assertThrows(NullPointerException.class, () -> {
                Bookmark bookmark = new Bookmark(new Item(1L, new User("Ole123", "123", "Ole Norman", "Ole@gmail.com", Role.USER), "test", 71.1655, 25.7992, "Troms og Finnmark", new Category("Mercedes", "Cars"), 200000), null);
            });
        }

    }

    @Nested
    class Null_variables{

        @Test
        void item_id_cannot_be_set_to_null(){
            Bookmark bookmark = new Bookmark(new Item(1L, new User("Ole123", "123", "Ole Norman", "Ole@gmail.com", Role.USER), "test", 71.1655, 25.7992, "Troms og Finnmark", new Category("Mercedes", "Cars"), 200000), new User("Ole123", "123", "Ole Norman", "Ole@gmail.com", Role.USER));
            assertThrows(NullPointerException.class, () -> {
                bookmark.setUser(null);
            });
        }

        @Test
        void user_id_cannot_be_set_to_null(){
            Bookmark bookmark = new Bookmark(new Item(1L, new User("Ole123", "123", "Ole Norman", "Ole@gmail.com", Role.USER), "test", 71.1655, 25.7992, "Troms og Finnmark", new Category("Mercedes", "Cars"), 200000), new User("Ole123", "123", "Ole Norman", "Ole@gmail.com", Role.USER));
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
            Item item = new Item(1L, user, "test", 71.1655, 25.7992, "Troms og Finnmark", new Category("Mercedes", "Cars"), 200000);
            Bookmark bookmark = new Bookmark(item, user);
            assertEquals(item, bookmark.getItem());
        }

        @Test
        void user_id_getter_returns_correct_value(){
            User user = new User("Ole123", "123", "Ole Norman", "Ole@gmail.com", Role.USER);
            Item item = new Item(1L, user, "test", 71.1655, 25.7992, "Troms og Finnmark", new Category("Mercedes", "Cars"), 200000);
            Bookmark bookmark = new Bookmark(item, user);
            assertEquals(user, bookmark.getUser());
        }
    }


}