package edu.ntnu.idatt2105.g6.backend.model.users;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookmarkId implements Serializable {


    @Column(name = "item_id")
    private Long item;

    @Column(name = "user_id")
    private Long user;

    public BookmarkId() {
    }

    public BookmarkId(Long item, Long user) {
        this.item = item;
        this.user = user;
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
