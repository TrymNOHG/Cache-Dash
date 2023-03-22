package edu.ntnu.idatt2105.g6.backend.exception.not_found;

public class CategoryNotFound extends NotFoundException {

    public CategoryNotFound(String categoryName) {
        super("Category", categoryName);
    }
    public CategoryNotFound(Long categoryId) {
        super("Category", categoryId);
    }

}
