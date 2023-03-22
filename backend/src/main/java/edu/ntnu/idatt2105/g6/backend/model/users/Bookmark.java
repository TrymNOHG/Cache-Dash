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
@IdClass(BookmarkId.class)
@Table(name = "bookmarks")
public class Bookmark {

    @Id
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("item")
    @JoinColumn(name = "item_id", nullable = false)
    @NonNull
    @ToString.Exclude
    private Item item;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("user")
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    @ToString.Exclude
    private User user;
}
