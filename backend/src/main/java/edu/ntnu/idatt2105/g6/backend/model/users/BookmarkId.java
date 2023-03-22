package edu.ntnu.idatt2105.g6.backend.model.users;

import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.MapsId;

import java.io.Serializable;
import java.util.Objects;


public class BookmarkId implements Serializable {


    private Long item;
    private String user;

    public BookmarkId() {
    }

    public BookmarkId(Long itemId, String username) {
        this.item = itemId;
        this.user = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookmarkId that)) return false;

        if (!Objects.equals(item, that.item)) return false;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        int result = item != null ? item.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
