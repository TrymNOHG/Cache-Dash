package edu.ntnu.idatt2105.g6.backend.mapper.listing;

import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryEditDTO;
import edu.ntnu.idatt2105.g6.backend.model.listing.Category;

public class CategoryMapper {

    public static Category toCategory(CategoryEditDTO categoryDTO, Category mainCategory){
        Category subCategory = Category
                .builder()
                .mainCategory(mainCategory)
                .subCategory(categoryDTO.subCategory())
                .build();
        return subCategory;
    }

//    public static Category toCategory(CategoryEditDTO categoryDTO){
//        Category category = Category
//                .builder()
//                .mainCategory(categoryDTO.mainCategory() != null ? categoryDTO.mainCategory() : )
//                .subCategory(categoryDTO.subCategory())
//                .build();
//        return category;
//    }

    public static CategoryDTO toCategoryDTO(Category rootCategory) {
        CategoryDTO categoryDTO = new CategoryDTO(rootCategory);

        return categoryDTO;
    }

}
