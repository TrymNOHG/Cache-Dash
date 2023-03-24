package edu.ntnu.idatt2105.g6.backend.exception.not_found;

public class ItemNotFoundException extends NotFoundException {

    public ItemNotFoundException(String itemName) {
        super("Item", itemName);
    }

    public ItemNotFoundException(Long itemId) {
        super("Item", itemId);
    }

}
