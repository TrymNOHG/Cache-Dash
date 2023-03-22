package edu.ntnu.idatt2105.g6.backend.model.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Nested
    class Null_columns_constructors {
        //TODO does not throw error for empty constructor

        @Test
        void sub_category_cannot_be_null(){
            assertThrows(NullPointerException.class, () -> {
                Category category = Category
                        .builder()
                        .subCategory(null)
                        .mainCategory(new Category())
                        .build();
            });
        }

//        @Test
//        void main_category_cannot_be_null(){
//            //TODO does not throw error for empty constructor
//            assertThrows(NullPointerException.class, () -> {
//                Category category = new Category("Mercedes", null);
//            });
//        }

    }

    @Nested
    class Null_variables{

        @Test
        void sub_category_cannot_be_set_to_null(){
            Category category = Category
                    .builder()
                    .subCategory("Mercedes")
                    .mainCategory(Category
                            .builder()
                            .subCategory("Cars")
                            .build())
                    .build();
            assertThrows(NullPointerException.class, () -> {
                category.setSubCategory(null);
            });
        }

        @Test
        void main_category_can_be_set_to_null(){
            Category category = Category
                    .builder()
                    .subCategory("Mercedes")
                    .mainCategory(Category
                            .builder()
                            .subCategory("Cars")
                            .build())
                    .build();
            assertDoesNotThrow(() -> category.setMainCategory(null));
        }
    }

    @Nested
    class Getters{

        @Test
        void sub_category_getter_returns_correct_value(){
            Category category = Category
                    .builder()
                    .subCategory("Mercedes")
                    .mainCategory(Category
                            .builder()
                            .subCategory("Cars")
                            .build())
                    .build();
            assertEquals("Mercedes", category.getSubCategory());
        }

        @Test
        void main_category_getter_returns_correct_value(){
            Category category = Category
                    .builder()
                    .subCategory("Mercedes")
                    .mainCategory(Category
                            .builder()
                            .subCategory("Cars")
                            .build())
                    .build();
            assertEquals("Cars", category.getMainCategory().getSubCategory());
        }
    }


}