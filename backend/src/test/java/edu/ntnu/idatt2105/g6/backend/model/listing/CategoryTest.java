package edu.ntnu.idatt2105.g6.backend.model.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryTest {

    @Nested
    class Null_columns_constructors {

        @Test
        void sub_category_cannot_be_null(){
            //TODO does not throw error for empty constructor
            assertThrows(NullPointerException.class, () -> {
                Category category = new Category(null, "Cars");
            });
        }

        @Test
        void main_category_cannot_be_null(){
            //TODO does not throw error for empty constructor
            assertThrows(NullPointerException.class, () -> {
                Category category = new Category("Mercedes", null);
            });
        }

    }

    @Nested
    class Null_variables{

        @Test
        void sub_category_cannot_be_set_to_null(){
            Category category = new Category("Mercedes", "Cars");
            assertThrows(NullPointerException.class, () -> {
                category.setSubCategory(null);
            });
        }

        @Test
        void main_category_cannot_be_set_to_null(){
            Category category = new Category("Mercedes", "Cars");
            assertThrows(NullPointerException.class, () -> {
                category.setMainCategory(null);
            });
        }
    }

    @Nested
    class Getters{

        @Test
        void sub_category_getter_returns_correct_value(){
            Category category = new Category("Mercedes", "Cars");
            assertEquals("Mercedes", category.getSubCategory());
        }

        @Test
        void main_category_getter_returns_correct_value(){
            Category category = new Category("Mercedes", "Cars");
            assertEquals("Cars", category.getMainCategory());
        }
    }


}