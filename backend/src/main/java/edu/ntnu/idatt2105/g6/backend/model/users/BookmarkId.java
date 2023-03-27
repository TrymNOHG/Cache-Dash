package edu.ntnu.idatt2105.g6.backend.model.users;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 A class that serves as the id for the Bookmark entity in the e-commerce app.
 It is an Embeddable class that contains two fields, item and user.
 */
@Embeddable
public class BookmarkId implements Serializable {


    /**
     * The id of the item being bookmarked.
     */
    @Column(name = "item_id")
    private Long item;

    /**
     * The id of the user who is bookmarking the item.
     */
    @Column(name = "user_id")
    private Long user;

    /**
     * Constructs a new BookmarkId object with null values for item and user.
     */
    public BookmarkId() {
    }

    /**
     * Constructs a new BookmarkId object with the given item and user ids.
     *
     * @param item the id of the item being bookmarked.
     * @param user the id of the user who is bookmarking the item.
     */
    public BookmarkId(Long item, Long user) {
        this.item = item;
        this.user = user;
    }

    /**
     * Determines whether this BookmarkId object is equal to the given object.
     *
     * @param o the object to compare to this BookmarkId object.
     * @return true if the given object is a BookmarkId object with the same item and user ids as this BookmarkId object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookmarkId that)) return false;

        if (!Objects.equals(item, that.item)) return false;
        return Objects.equals(user, that.user);
    }

    /**
     * Generates a hash code for this BookmarkId object.
     *
     * @return a hash code based on the item and user ids of this BookmarkId object.
     */
    @Override
    public int hashCode() {
        int result = item != null ? item.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
