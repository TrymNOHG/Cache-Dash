package edu.ntnu.idatt2105.g6.backend.model.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.listing.KeyInfo;
import edu.ntnu.idatt2105.g6.backend.model.users.Bookmark;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemTest {

    @Nested
    class Null_columns_constructors {

//        @Test
//        void item_id_cannot_be_null(){
//            //TODO does not throw error for empty constructor
//            User user = new User("test1", "123", "test1", "test1", Role.USER);
//            assertThrows(NullPointerException.class, () -> {
//                Item item = new Item(null, user, "test",  "Nordkapp", "Troms og Finnmark",new Category(), 200000);
//            });
//        }

        @Test
        void user_cannot_be_null(){
            //TODO does not throw error for empty constructor
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            assertThrows(NullPointerException.class, () -> {
                Item item = new Item(null, "test",  "Nordkapp", "Troms og Finnmark",new Category(), 200000);
            });
        }

        @Test
        void brief_description_cannot_be_null(){
            //TODO does not throw error for empty constructor
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            assertThrows(NullPointerException.class, () -> {
                Item item = new Item(user, null,  "Nordkapp", "Troms og Finnmark", new Category(), 200000);
            });
        }

        @Test
        void county_cannot_be_null(){
            //TODO does not throw error for empty constructor
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            assertThrows(NullPointerException.class, () -> {
                Item item = new Item(user, "test",  "Nordkapp", null, new Category(),  200000);
            });
        }

        @Test
        void category_cannot_be_null(){
            //TODO does not throw error for empty constructor
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            assertThrows(NullPointerException.class, () -> {
                Item item = new Item(user, "test",  "Nordkapp", "Troms og Finnmark", null ,200000);
            });
        }

    }

    @Nested
    class Null_variables{

//        @Test
//        void item_id_cannot_be_set_to_null(){
//            User user = new User("test1", "123", "test1", "test1", Role.USER);
//            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
//            Item item = new Item(user, "test",  "Nordkapp", "Troms og Finnmark", category, 200000);
//            assertThrows(NullPointerException.class, () -> {
//                item.setItemId(null);
//            });
//        }

        @Test
        void user_cannot_be_set_to_null(){
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
            Item item = new Item(user, "test",  "Nordkapp", "Troms og Finnmark", category, 200000);
            assertThrows(NullPointerException.class, () -> {
                item.setUser(null);
            });
        }

        @Test
        void brief_description_cannot_be_set_to_null(){
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
            Item item = new Item(user, "test",  "Nordkapp", "Troms og Finnmark", category, 200000);
            assertThrows(NullPointerException.class, () -> {
                item.setBriefDesc(null);
            });
        }

        @Test
        void county_cannot_be_set_to_null(){
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
            Item item = new Item(user, "test",  "Nordkapp", "Troms og Finnmark", category, 200000);
            assertThrows(NullPointerException.class, () -> {
                item.setCounty(null);
            });
        }

        @Test
        void category_cannot_be_set_to_null(){
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
            Item item = new Item(user, "test",  "Nordkapp", "Troms og Finnmark", category, 200000);
            assertThrows(NullPointerException.class, () -> {
                item.setCategory(null);
            });
        }
    }

    @Nested
    class Getters{

        @Test
        void item_id_getter_returns_correct_value(){
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
            byte[] thumbnail = new byte[5];
            List<KeyInfo> keyInfoList = new ArrayList<>();
            List<Bookmark> bookmarkerList = new ArrayList<>();
            Item item = new Item(1L, user, "test", "test1", "Nordkapp", "Troms og Finnmark", category, 200000, thumbnail, keyInfoList, bookmarkerList);
            assertEquals(1L, item.getItemId());
        }

        @Test
        void user_getter_returns_correct_value(){
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
            byte[] thumbnail = new byte[5];
            List<KeyInfo> keyInfoList = new ArrayList<>();
            List<Bookmark> bookmarkerList = new ArrayList<>();
            Item item = new Item(1L, user, "test", "test1", "Nordkapp", "Troms og Finnmark", category, 200000, thumbnail, keyInfoList, bookmarkerList);
            assertEquals(user, item.getUser());
        }

        @Test
        void brief_description_getter_returns_correct_value(){
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
            byte[] thumbnail = new byte[5];
            List<KeyInfo> keyInfoList = new ArrayList<>();
            List<Bookmark> bookmarkerList = new ArrayList<>();
            Item item = new Item(1L, user, "test", "test1", "Nordkapp", "Troms og Finnmark", category, 200000, thumbnail, keyInfoList, bookmarkerList);
            assertEquals("test", item.getBriefDesc());
        }

        @Test
        void full_description_getter_returns_correct_value(){
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
            byte[] thumbnail = new byte[5];
            List<KeyInfo> keyInfoList = new ArrayList<>();
            List<Bookmark> bookmarkerList = new ArrayList<>();
            Item item = new Item(1L, user, "test", "test1", "Nordkapp", "Troms og Finnmark", category, 200000, thumbnail, keyInfoList, bookmarkerList);
            assertEquals("test1", item.getFullDesc());
        }

        @Test
        void address_getter_returns_correct_value(){
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
            byte[] thumbnail = new byte[5];
            List<KeyInfo> keyInfoList = new ArrayList<>();
            List<Bookmark> bookmarkerList = new ArrayList<>();
            Item item = new Item(1L, user, "test", "test1", "Nordkapp", "Troms og Finnmark", category, 200000, thumbnail, keyInfoList, bookmarkerList);
            assertEquals("Nordkapp", item.getAddress());
        }


        @Test
        void county_getter_returns_correct_value(){
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
            byte[] thumbnail = new byte[5];
            List<KeyInfo> keyInfoList = new ArrayList<>();
            List<Bookmark> bookmarkerList = new ArrayList<>();
            Item item = new Item(1L, user, "test", "test1", "Nordkapp", "Troms og Finnmark", category, 200000, thumbnail, keyInfoList, bookmarkerList);
            assertEquals("Troms og Finnmark", item.getCounty());
        }

        @Test
        void category_getter_returns_correct_value(){
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
            byte[] thumbnail = new byte[5];
            List<KeyInfo> keyInfoList = new ArrayList<>();
            List<Bookmark> bookmarkerList = new ArrayList<>();
            Item item = new Item(1L, user, "test", "test1", "Nordkapp", "Troms og Finnmark", category, 200000, thumbnail, keyInfoList, bookmarkerList);
            assertEquals(category, item.getCategory());
        }

        @Test
        void price_getter_returns_correct_value(){
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
            byte[] thumbnail = new byte[5];
            List<KeyInfo> keyInfoList = new ArrayList<>();
            List<Bookmark> bookmarkerList = new ArrayList<>();
            Item item = new Item(1L, user, "test", "test1", "Nordkapp", "Troms og Finnmark", category, 200000, thumbnail, keyInfoList, bookmarkerList);
            assertEquals(200000, item.getPrice());
        }

        @Test
        void thumbnail_getter_returns_correct_value(){
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
            byte[] thumbnail = new byte[5];
            List<KeyInfo> keyInfoList = new ArrayList<>();
            List<Bookmark> bookmarkerList = new ArrayList<>();
            Item item = new Item(1L, user, "test", "test1", "Nordkapp", "Troms og Finnmark", category, 200000, thumbnail, keyInfoList, bookmarkerList);
            assertEquals(thumbnail, item.getThumbnail());
        }

        @Test
        void key_info_list_getter_returns_correct_value(){
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
            byte[] thumbnail = new byte[5];
            List<KeyInfo> keyInfoList = new ArrayList<>();
            List<Bookmark> bookmarkerList = new ArrayList<>();
            Item item = new Item(1L, user, "test", "test1", "Nordkapp", "Troms og Finnmark", category, 200000, thumbnail, keyInfoList, bookmarkerList);
            assertEquals(keyInfoList, item.getKeyInfoList());
        }

        @Test
        void bookmarker_list_getter_returns_correct_value(){
            User user = new User("test1", "123", "test1", "test1", Role.USER);
            Category category = Category.builder().categoryId(1L).subCategory("Cars").build();
            byte[] thumbnail = new byte[5];
            List<KeyInfo> keyInfoList = new ArrayList<>();
            List<Bookmark> bookmarkerList = new ArrayList<>();
            Item item = new Item(1L, user, "test", "test1", "Nordkapp", "Troms og Finnmark", category, 200000, thumbnail, keyInfoList, bookmarkerList);
            assertEquals(bookmarkerList, item.getBookmarkerList());
        }
    }


}