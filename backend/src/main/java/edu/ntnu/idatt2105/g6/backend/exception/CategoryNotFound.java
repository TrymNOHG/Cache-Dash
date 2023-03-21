package edu.ntnu.idatt2105.g6.backend.exception;

import edu.ntnu.idatt2105.g6.backend.model.listing.Category;

public class CategoryNotFound extends RuntimeException{

    public CategoryNotFound(String categoryName) {
        super(String.format("Category %s does not exist", categoryName));
    }

}
