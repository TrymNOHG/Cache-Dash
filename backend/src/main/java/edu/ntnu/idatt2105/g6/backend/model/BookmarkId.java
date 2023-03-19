package edu.ntnu.idatt2105.g6.backend.model;

import java.io.Serializable;
import java.util.Objects;

public class BookmarkId implements Serializable {

    private Long itemId;
    private Long userId;

    public BookmarkId() {
    }

    public BookmarkId(Long itemId, Long userId) {
        this.itemId = itemId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookmarkId that)) return false;

        if (!Objects.equals(itemId, that.itemId)) return false;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
