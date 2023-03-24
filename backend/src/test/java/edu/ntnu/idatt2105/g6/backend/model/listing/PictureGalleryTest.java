package edu.ntnu.idatt2105.g6.backend.model.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.listing.PictureGallery;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PictureGalleryTest {

    @Nested
    class When_Picture_Gallery_constructor_has_non_null_variable {

//        @Test
//        public void id_throws_NullPointer_Exception_if_null() {
//            assertThrows(NullPointerException.class, () -> {
//                PictureGallery pictureGallery = new PictureGallery(null, new Item(), new byte[5]);
//            });
//        }
        @Test
        public void item_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                PictureGallery pictureGallery = new PictureGallery(1L, null, new byte[5]);
            });
        }

        @Test
        public void picture_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                PictureGallery pictureGallery = new PictureGallery(1L, new Item(), null);
            });
        }

    }

    @Nested
    class When_setting_Picture_Gallery_non_null_variable {
//        @Test
//        public void id_throws_NullPointer_Exception_if_null() {
//            assertThrows(NullPointerException.class, () -> {
//                PictureGallery pictureGallery = new PictureGallery();
//                pictureGallery.setPictureGalleryId(null);
//            });
//        }
        @Test
        public void item_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                PictureGallery pictureGallery = new PictureGallery();
                pictureGallery.setItem(null);
            });
        }

        @Test
        public void picture_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                PictureGallery pictureGallery = new PictureGallery();
                pictureGallery.setPicture(null);
            });
        }
    }

    @Nested
    class Picture_Gallery_can_properly_get {
        PictureGallery getPictureGallery() {
            return new PictureGallery(1L, new Item(), new byte[5]);
        }

        @Test
        void id() {
            PictureGallery pictureGallery = getPictureGallery();
            Long expectedId = 1L;
            Long actualId = pictureGallery.getPictureGalleryId();

            assertEquals(expectedId, actualId);
        }

        @Test
        void item() {
            PictureGallery pictureGallery = getPictureGallery();
            Item expectedItem = new Item();
            pictureGallery.setItem(expectedItem);

            Item actualItem = pictureGallery.getItem();

            assertEquals(expectedItem, actualItem);
        }

        @Test
        void picture() {
            PictureGallery pictureGallery = getPictureGallery();
            byte[] expectedPicture = new byte[5];
            pictureGallery.setPicture(expectedPicture);

            byte[] actualPicture = pictureGallery.getPicture();

            assertEquals(expectedPicture, actualPicture);
        }

    }

}