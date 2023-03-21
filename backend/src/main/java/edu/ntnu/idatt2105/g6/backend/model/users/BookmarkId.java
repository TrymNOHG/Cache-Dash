package edu.ntnu.idatt2105.g6.backend.model.users;

import java.io.Serializable;
import java.util.Objects;

public class BookmarkId implements Serializable {

    private Long itemId;
    private String username;

    public BookmarkId() {
    }

    public BookmarkId(Long itemId, String username) {
        this.itemId = itemId;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookmarkId that)) return false;

        if (!Objects.equals(itemId, that.itemId)) return false;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
