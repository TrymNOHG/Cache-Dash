package edu.ntnu.idatt2105.g6.backend.service.listing;

import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryEditDTO;
import edu.ntnu.idatt2105.g6.backend.model.listing.Category;

import java.util.List;

public interface ICategoryService {

    CategoryDTO loadAllCategory();
    void addCategory(CategoryEditDTO categoryDTO);
    void deleteCategory(CategoryEditDTO categoryEditDTO);
    CategoryDTO loadSubCategories(Long mainCategoryId);
    List<CategoryDTO> loadSubCategoriesShallow(Long mainCategoryId);
    CategoryDTO loadCategoryById(Long categoryId);

}
