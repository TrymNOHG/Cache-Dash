package edu.ntnu.idatt2105.g6.backend.mapper.listing;

import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryEditDTO;
import edu.ntnu.idatt2105.g6.backend.model.listing.Category;

/**
 The CategoryMapper class is responsible for mapping Category objects to CategoryDTO objects and vice versa.
 */
public class CategoryMapper {

    /**
     * Maps a CategoryEditDTO to a subcategory of a main Category.
     *
     * @param categoryDTO The CategoryEditDTO containing the subcategory information.
     * @param mainCategory The main Category to which the subcategory belongs.
     * @return The newly created Category object.
     */
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

    /**
     * Maps a Category object to a CategoryDTO object.
     *
     * @param rootCategory The Category object to map.
     * @return The newly created CategoryDTO object.
     */
    public static CategoryDTO toCategoryDTO(Category rootCategory) {
        CategoryDTO categoryDTO = new CategoryDTO(rootCategory);

        return categoryDTO;
    }

}
