package edu.ntnu.idatt2105.g6.backend.exception;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(Long itemId) {
        super(String.format("Item with ID %d does not exist.", itemId));
    }

}
