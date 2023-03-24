package edu.ntnu.idatt2105.g6.backend.model.users;

import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "bookmarks")
public class Bookmark {

    @EmbeddedId
    private BookmarkId id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("item")
    @JoinColumn(name = "item_id", nullable = false)
    @NonNull
    @ToString.Exclude
    private Item item;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("user")
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    @ToString.Exclude
    private User user;
}
