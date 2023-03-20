package edu.ntnu.idatt2105.g6.backend.model;

import edu.ntnu.idatt2105.g6.backend.model.users.BookmarkId;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BookmarkIdTest {

    @Nested
    class Constructor_test{

        @Test
        void empty_constructor_does_not_throw_errors(){
            assertDoesNotThrow(() -> {
                new BookmarkId();
            });
        }

        @Test
        void constructor_does_not_throw_errors(){
            assertDoesNotThrow(() -> {
                new BookmarkId(1L, "test");
            });
        }
    }
}
