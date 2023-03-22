package edu.ntnu.idatt2105.g6.backend.model.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.listing.KeyInfo;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyInfoTest {

    @Nested
    class When_KeyInfo_constructor_has_non_null_variable {
        @Test
        public void id_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                KeyInfo keyInfo = new KeyInfo(null, new Item(), "Apple");
            });
        }
        @Test
        public void item_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                KeyInfo keyInfo = new KeyInfo(1L, null, "Apple");

            });
        }


        @Test
        public void description_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                KeyInfo keyInfo = new KeyInfo(1L, new Item(),null);
            });
        }

    }

    @Nested
    class When_setting_KeyInfo_non_null_variable {
        @Test
        public void id_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                KeyInfo keyInfo = new KeyInfo();
                keyInfo.setKeyInfoId(null);
            });
        }
        @Test
        public void item_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                KeyInfo keyInfo = new KeyInfo();
                keyInfo.setItem(null);
            });
        }


        @Test
        public void description_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                KeyInfo keyInfo = new KeyInfo();
                keyInfo.setDescription(null);
            });
        }

    }

    @Nested
    class Message_can_properly_get {
        KeyInfo getKeyInfo() {
            return new KeyInfo(1L, new Item(),"Apple");
        }

        @Test
        void id() {
            KeyInfo keyInfo = getKeyInfo();
            Long expectedId = 1L;
            Long actualId = keyInfo.getKeyInfoId();

            assertEquals(expectedId, actualId);
        }

        @Test
        void item() {
            KeyInfo keyInfo = getKeyInfo();
            Item expectedItem = new Item();
            keyInfo.setItem(expectedItem);

            Item actualItem = keyInfo.getItem();

            assertEquals(expectedItem, actualItem);
        }

        @Test
        void description() {
            KeyInfo keyInfo = getKeyInfo();
            String expectedDescription = "Apple";
            String actualDescription = keyInfo.getDescription();

            assertEquals(expectedDescription, actualDescription);
        }

    }

}