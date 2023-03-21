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

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    //TODO: Should admin be able to change an already existing Category? Then, need new DTO maybe.

    @Override
    public CategoryDTO loadAllCategory() {
        return CategoryMapper
                .toCategoryDTO(categoryRepository.findByMainCategory_SubCategory(null)
                .orElseThrow(() -> new CategoryNotFound("Root")).get(0));
    }

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
            mainCategory = categoryRepository.findBySubCategory(null)
                    .orElseGet(() -> categoryRepository.save(Category
                            .builder()
                            .mainCategory(null)
                            .subCategory(null)
                            .build()));
        }

        /* Check if root already has sub-category with that name */
        if(mainCategory.getSubCategories()
                .stream()
                .anyMatch(subCategory -> subCategory.getSubCategory().equals(categoryDTO.subCategory()))) {
            throw new CategoryExistsException(categoryDTO.subCategory());
        }

        categoryRepository.save(CategoryMapper.toCategory(categoryDTO, mainCategory));
    }

    @Override
    public void deleteCategory(CategoryEditDTO categoryDTO) {
        User user = userRepository.findById(categoryDTO.userId())
                .orElseThrow(() -> new UserNotFoundException(categoryDTO.userId()));
        if(user.getRole() != Role.ADMIN) throw new UnauthorizedException(user.getUsername());
        Objects.requireNonNull(categoryDTO.categoryId());
        userRepository.deleteById(categoryDTO.categoryId());
    }

    /**
     * This method finds the root category, given as a String. This root category holds a list of its
     * sub-categories, which continues downwards.
     * @param mainCategory  Name of root category, given as a String.
     * @return              Main category, wrapped in a CategoryDTO object.
     */
    @Override
    public CategoryDTO loadSubCategories(String mainCategory) {
        return CategoryMapper.toCategoryDTO(
                categoryRepository.findBySubCategory(mainCategory)
                .orElseThrow(() -> new CategoryNotFound(mainCategory))
        );
    }

}
