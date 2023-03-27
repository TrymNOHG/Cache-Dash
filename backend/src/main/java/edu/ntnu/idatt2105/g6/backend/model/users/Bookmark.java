package edu.ntnu.idatt2105.g6.backend.model.users;

import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import jakarta.persistence.*;
import lombok.*;

/**
 Represents a bookmark that a user has created for an item in the e-commerce app.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "bookmarks")
public class Bookmark {

    /**
     * The composite primary key of the bookmark, consisting of the user id and the item id.
     */
    @EmbeddedId
    private BookmarkId id;

    /**
     * The item that is bookmarked.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("item")
    @JoinColumn(name = "item_id", nullable = false)
    @NonNull
    @ToString.Exclude
    private Item item;

    /**
     * The user that has created the bookmark.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("user")
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    @ToString.Exclude
    private User user;
}
