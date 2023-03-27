package edu.ntnu.idatt2105.g6.backend.repo.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**

 This repository provides CRUD operations for the Category entity.

 It extends JpaRepository and JpaSpecificationExecutor interfaces.

 JpaRepository provides basic CRUD operations while JpaSpecificationExecutor provides
 search functionality using specifications.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Find a Category by its subCategory name
     *
     * @param subCategory The subCategory name of the Category to find
     * @return An Optional containing the Category found, or an empty Optional if not found
     */
    Optional<Category> findBySubCategory(String subCategory);

    /**
     * Find all Categories by a main category name
     *
     * @param mainCategoryName The main category name of the Categories to find
     * @return An Optional containing a List of Categories found, or an empty Optional if not found
     */
    Optional<List<Category>> findByMainCategory_SubCategory(String mainCategoryName);

    /**
     * Find a Category by its ID
     *
     * @param categoryId The ID of the Category to find
     * @return An Optional containing the Category found, or an empty Optional if not found
     */
    Optional<Category> findCategoryByCategoryId(Long categoryId);

}
