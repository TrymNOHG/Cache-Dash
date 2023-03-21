package edu.ntnu.idatt2105.g6.backend.exception.exists;

public class CategoryExistsException extends ExistsException{

    public CategoryExistsException(String categoryName) {
        super("Category", categoryName);
    }

}
