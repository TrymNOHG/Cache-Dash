package edu.ntnu.idatt2105.g6.backend.service.listing;

import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryDeletionDTO;

public interface ICategoryService {

    void loadAllCategory();
    void addCategory(CategoryDTO categoryDTO);
    void deleteCategory(CategoryDeletionDTO categoryDeletionDTO);
    void loadCategory();

}
