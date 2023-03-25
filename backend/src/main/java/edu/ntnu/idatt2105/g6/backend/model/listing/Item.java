package edu.ntnu.idatt2105.g6.backend.model.listing;

import edu.ntnu.idatt2105.g6.backend.model.users.Bookmark;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

/**

 Represents an item for sale or rent in a marketplace.
 An item has an id, seller, brief and full descriptions, address, county, category, price,
 thumbnail image, status, key information, and a list of bookmarkers.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    /**
     * The unique identifier for this item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    /**
     * The user who listed this item.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NonNull
    @ToString.Exclude
    private User user;

    /**
     * A brief description of this item.
     */
    @Column(name = "brief_desc", nullable = false)
    @NonNull
    private String briefDesc;

    /**
     * The full description of this item.
     */
    @Column(name = "full_desc")
    private String fullDesc;

    /**
     * The address where this item is located.
     */
    @Column(name = "address", nullable = false)
    @NonNull
    private String address;

    /**
     * The county where this item is located.
     */
    @Column(name = "county", nullable = false)
    @NonNull
    private String county;

    /**
     * The category this item belongs to.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    @NonNull
    @ToString.Exclude
    private Category category;

    /**
     * The price of this item.
     */
    @Column(name = "price", nullable = false)
    @NonNull
    private double price;

    /**
     * The thumbnail image for this item.
     */
    @Column(name = "thumbnail")
    private byte[] thumbnail;

    /**
     * The current status of this item.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NonNull
    private ListingStatus status;

    /**
     * The key information about this item.
     */
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<KeyInfo> keyInfoList;

    /**
     * The bookmarks for this item.
     */
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<Bookmark> bookmarkerList = new ArrayList<>();

}
