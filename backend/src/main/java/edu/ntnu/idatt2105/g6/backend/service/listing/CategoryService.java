package edu.ntnu.idatt2105.g6.backend.service.listing;

import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryEditDTO;
import edu.ntnu.idatt2105.g6.backend.exception.exists.CategoryExistsException;
import edu.ntnu.idatt2105.g6.backend.exception.UnauthorizedException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.CategoryNotFound;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.UserNotFoundException;
import edu.ntnu.idatt2105.g6.backend.mapper.listing.CategoryMapper;
import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.listing.CategoryRepository;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Objects;

/**
 This service class handles the business logic for category-related operations.
 It implements the ICategoryService interface.
 */
@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    //TODO: Should admin be able to change an already existing Category? Then, need new DTO maybe.

    /**
     This method loads all the categories available in the application.

     @return CategoryDTO object representing the root category.
     @throws CategoryNotFound If no root category exists in the database.
     */
    @Override
    public CategoryDTO loadAllCategory() {
        return CategoryMapper
                .toCategoryDTO(categoryRepository.findByMainCategory_SubCategory(null)
                .orElseThrow(() -> new CategoryNotFound("Root")).get(0));
    }

    /**

     This method adds a new category to the application.

     @param categoryDTO The CategoryEditDTO object representing the new category.
     @throws CategoryExistsException If the given category name already exists for the same parent category.
     @throws UserNotFoundException If the user trying to add the category is not found in the database.
     @throws UnauthorizedException If the user trying to add the category is not an admin.
     @throws CategoryNotFound If the main category for the new category is not found in the database.
     */
    @Transactional
    @Override
    public void addCategory(CategoryEditDTO categoryDTO) {

        /*
            Currently, if the main category is null, then the root category is set as the main category obj.
            Additionally, if the root category has not been set yet, then the root category is constructed.

            editDTO call to without root: (1L, "Sports", null)
                ***Root Constructed***
                Root -> Sports
            editDTO call: (1L, "Football", "Sports")
                **Football Category Added***
                Root -> Sports -> Football

            editDTO call to  root: (1L, "School", null)
                **School Category Added***
                School <- Root -> Sports -> Football
         */
        //TEST:
        /*
            - Two subcategories of same main category cannot have same name
            - Root category is constructed properly
            - Unauthorized user cannot add category
         */

        User user = userRepository.findById(categoryDTO.userId())
                .orElseThrow(() -> new UserNotFoundException(categoryDTO.userId()));
        if(user.getRole() != Role.ADMIN) throw new UnauthorizedException(user.getUsername());

        Category mainCategory = categoryDTO.categoryId() != null ?
                categoryRepository.findById(categoryDTO.categoryId())
                .orElseThrow(() -> new CategoryNotFound(categoryDTO.categoryId()))
                : null;

        /* Create the root of the category tree, if it doesn't already exist */
        if (mainCategory == null) {
            mainCategory = categoryRepository.save(Category
                            .builder()
                            .categoryId(1L)
                            .mainCategory(null)
                            .subCategory("Root")
                            .subCategories(new HashSet<>())
                            .build());
        }

        /* Check if root already has sub-category with that name */
        if(mainCategory.getSubCategories()
                .stream()
                .anyMatch(subCategory -> subCategory.getSubCategory().equals(categoryDTO.subCategory()))) {
            throw new CategoryExistsException(categoryDTO.subCategory());
        }

        categoryRepository.save(CategoryMapper.toCategory(categoryDTO, mainCategory));
    }

    //TODO: add to categoryID 1 or create new root

    /**
     This method deletes a category from the application.

     @param categoryDTO The CategoryEditDTO object representing the category to be deleted.
     @throws UserNotFoundException If the user trying to delete the category is not found in the database.
     @throws UnauthorizedException If the user trying to delete the category is not an admin.
     */
    @Transactional
    @Override
    public void deleteCategory(CategoryEditDTO categoryDTO) {
        User user = userRepository.findById(categoryDTO.userId())
                .orElseThrow(() -> new UserNotFoundException(categoryDTO.userId()));
        if(user.getRole() != Role.ADMIN) throw new UnauthorizedException(user.getUsername());
        Objects.requireNonNull(categoryDTO.categoryId());
        categoryRepository.deleteById(categoryDTO.categoryId());
    }



    /**
     This method loads all the subcategories of a given root category.

     @param mainCategory The name of the root category as a String.
     @return The CategoryDTO object representing the root category and its subcategories.
     @throws CategoryNotFound If the root category is not found in the database.
     */
    @Override
    public CategoryDTO loadSubCategories(String mainCategory) {
        return CategoryMapper.toCategoryDTO(
                categoryRepository.findBySubCategory(mainCategory)
                .orElseThrow(() -> new CategoryNotFound(mainCategory))
        );
    }

}
