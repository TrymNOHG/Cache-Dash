package edu.ntnu.idatt2105.g6.backend.model;

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
    @Column(name = "item_id", nullable = false)
    @NonNull
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    @NonNull
    @ToString.Exclude
    private User user;

    @Column(name = "brief_desc", nullable = false)
    @NonNull
    private String briefDesc;

    @Column(name = "full_desc")
    private String fullDesc;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "county", nullable = false)
    @NonNull
    private String county;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category")
    @NonNull
    @ToString.Exclude
    private Category category;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "thumbnail")
    private byte[] thumbnail;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<KeyInfo> keyInfoList = new ArrayList<>();

    @OneToMany
    @ToString.Exclude
    private List<User> bookmarkers = new ArrayList<>();

}
