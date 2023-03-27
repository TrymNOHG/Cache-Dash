package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryEditDTO;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.listing.CategoryRepository;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import edu.ntnu.idatt2105.g6.backend.service.listing.CategoryService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
public class CategoryServiceIntegrationTest {



    @Nested
    @SpringBootTest
    class AddCategory{

        @Autowired
        UserRepository userRepository;

        @Autowired
        CategoryService categoryService;

        @Autowired
        CategoryRepository categoryRepository;

        @Test
        void addCategory_adds_new_category(){
            User user = User
                    .builder()
                    .userId(1L)
                    .username("OleN")
                    .password("password")
                    .fullName("Ole Norman")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(user);

            CategoryEditDTO categoryEditDTO = new CategoryEditDTO(1l, "testCars",null);
            assertDoesNotThrow(() -> {
                categoryService.addCategory(categoryEditDTO);
            });

        }

    }


    @Nested
    @SpringBootTest
    class DeleteCategory{
        @Autowired
        UserRepository userRepository;

        @Autowired
        CategoryService categoryService;

        @Autowired
        CategoryRepository categoryRepository;

        @Test
        void deleteCategory_deletes_category(){
            User user = User
                    .builder()
                    .userId(1L)
                    .username("OleN")
                    .password("password")
                    .fullName("Ole Norman")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(user);

            CategoryEditDTO categoryEditDTO = new CategoryEditDTO(1l, "testCars",null);
            categoryService.addCategory(categoryEditDTO);
            CategoryEditDTO categoryEditDTO2 = new CategoryEditDTO(1l, "testCars",1L);

            assertDoesNotThrow(() -> {
                categoryService.deleteCategory(categoryEditDTO2);
            });

        }


    }

    @Nested
    @SpringBootTest
    class LoadAllCategory{

        @Autowired
        UserRepository userRepository;

        @Autowired
        CategoryService categoryService;

        @Autowired
        CategoryRepository categoryRepository;

        @Test
        void loadAllCategory_does_not_throw_exceptions(){
            User user = User
                    .builder()
                    .userId(1L)
                    .username("OleN")
                    .password("password")
                    .fullName("Ole Norman")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(user);

            CategoryEditDTO categoryEditDTO = new CategoryEditDTO(1l, "testCars",null);
            categoryService.addCategory(categoryEditDTO);
            assertDoesNotThrow(() -> {
                categoryService.loadAllCategory();
            });

        }

    }

}
