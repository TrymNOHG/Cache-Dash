package edu.ntnu.idatt2105.g6.backend.model;

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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("itemId")
    @JoinColumn(name = "item_id", nullable = false)
    @NonNull
    @ToString.Exclude
    private Item item;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("username")
    @JoinColumn(name = "username", nullable = false)
    @NonNull
    @ToString.Exclude
    private User user;
}
