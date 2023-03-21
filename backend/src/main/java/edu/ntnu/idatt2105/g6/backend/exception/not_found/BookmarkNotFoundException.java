package edu.ntnu.idatt2105.g6.backend.exception.not_found;

public class BookmarkNotFoundException extends NotFoundException{

    public BookmarkNotFoundException(String value) {
        super("Bookmark", value);
    }

    public BookmarkNotFoundException(Long value) {
        super("Bookmark", value);
    }
}
