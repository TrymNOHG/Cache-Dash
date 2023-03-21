package edu.ntnu.idatt2105.g6.backend.repo.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findBySubCategory(String subCategory);
    Optional<List<Category>> findByMainCategory(String mainCategory);

}
