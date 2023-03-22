package edu.ntnu.idatt2105.g6.backend.model.listing;

import edu.ntnu.idatt2105.g6.backend.model.users.Bookmark;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NonNull
    @ToString.Exclude
    private User user;

    @Column(name = "brief_desc", nullable = false)
    @NonNull
    private String briefDesc;

    @Column(name = "full_desc")
    private String fullDesc;

    @Column(name = "address", nullable = false)
    @NonNull
    private String address;


    @Column(name = "county", nullable = false)
    @NonNull
    private String county;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @NonNull
    @ToString.Exclude
    private Category category;

    @Column(name = "price", nullable = false)
    @NonNull
    private double price;

    @Column(name = "thumbnail")
    private byte[] thumbnail;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<KeyInfo> keyInfoList;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Bookmark> bookmarkerList = new ArrayList<>();

}
