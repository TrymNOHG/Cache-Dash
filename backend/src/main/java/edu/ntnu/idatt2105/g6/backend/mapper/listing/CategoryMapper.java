package edu.ntnu.idatt2105.g6.backend.mapper.listing;

import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryDTO;
import edu.ntnu.idatt2105.g6.backend.model.listing.Category;

public class CategoryMapper {

    public static Category toCategory(CategoryDTO categoryDTO){
        Category category = Category
                .builder()
                .mainCategory(categoryDTO.getMainCategory())
                .subCategory(categoryDTO.getSubCategory())
                .build();
        return category;
    }
}
