package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryEditDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.listing.CategoryRepository;
import edu.ntnu.idatt2105.g6.backend.repo.listing.ItemRepository;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import edu.ntnu.idatt2105.g6.backend.service.listing.CategoryService;
import edu.ntnu.idatt2105.g6.backend.service.listing.ItemService;
import edu.ntnu.idatt2105.g6.backend.service.security.AuthenticationService;
import org.junit.Before;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
public class ItemServiceIntegrationTest {



    @Nested
    @SpringBootTest
    class Item_can_be{
        @Autowired
        UserRepository userRepository;

        @Autowired
        ItemRepository itemRepository;

        @Autowired
        CategoryRepository categoryRepository;

        @Autowired
        ItemService itemService;

        @Autowired
        CategoryService categoryService;

        ListingDTO populateDB() {
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

            CategoryEditDTO categoryEditDTO = new CategoryEditDTO(user.getUserId(), "Mercedes", null);
            categoryService.addCategory(categoryEditDTO);

            System.out.println(categoryRepository.findAll().get(1).getMainCategory());

            return ListingDTO
                    .builder()
                    .username(user.getUsername())
                    .briefDesc("Yamaha Piano")
                    .address("Trondheim")
                    .county("Troenderlag")
                    .categoryId(1L)
                    .price(1000)
                    .build();
        }


        @Test
        void added_to_database(){
            String expectedBriefDesc = "Yamaha Piano";
            ListingDTO listingDTO = populateDB();
            //when(userRepository.findByUsername("Test")).thenReturn(Optional.ofNullable(user));
            //when(categoryRepository.findBySubCategory("Mercedes")).thenReturn(Optional.ofNullable(category));
            assertDoesNotThrow(() -> {
                itemService.addListing(listingDTO);
            });

            String actualBriefDesc = itemRepository.findByItemId(1L).orElseThrow().getBriefDesc();

            assertEquals(expectedBriefDesc, actualBriefDesc);
        }

    }


    @Nested
    @SpringBootTest
    class Listing_can_be {
        @Autowired
        UserRepository userRepository;

        @Autowired
        ItemRepository itemRepository;

        @Autowired
        CategoryRepository categoryRepository;

        @Autowired
        ItemService itemService;

        @Autowired
        CategoryService categoryService;

        @Before
        void setUp() {

        }


        @Test
        public void loaded_correctly_by_Id(){

            User user = User
                    .builder()
                    .username("Test")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();
            Category category = Category
                    .builder()
                    .subCategory("Mercedes")
                    .mainCategory(null)
                    .build();

            CategoryEditDTO categoryEditDTO = new CategoryEditDTO(1L, "Mercedes", null);
            userRepository.save(user);
            categoryService.addCategory(categoryEditDTO);


            ListingDTO expectedListingDTO = new ListingDTO(1L, "Test", "desc", "Nordkapp", "Troms og Finnmark", 1L, 100);
            //when(userRepository.findByUsername("Test")).thenReturn(Optional.ofNullable(user));
            //when(categoryRepository.findBySubCategory("Mercedes")).thenReturn(Optional.ofNullable(category));
            assertDoesNotThrow(() -> {
                itemService.addListing(expectedListingDTO);
            });

            ListingDTO actualListingDTO = itemService.loadListing(1L);

            assertEquals(expectedListingDTO.getCategoryId(), actualListingDTO.getCategoryId());
            assertEquals(expectedListingDTO.getUsername(), actualListingDTO.getUsername());
            assertEquals(expectedListingDTO.getItemId(), actualListingDTO.getItemId());

        }

        @Test
        void added_to_database() {

        }

        @Test
        void removed_from_database() {

        }

    }

    @Nested
    @SpringBootTest
    class Updating_listing_variables {

        @Test
        void county_is_successful(){

        }
    }

}
