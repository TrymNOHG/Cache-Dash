package edu.ntnu.idatt2105.g6.backend.model.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import jakarta.persistence.*;
import lombok.*;

/**
 This class represents the key information associated with an item in the e-commerce app.

 The key information is a brief description of the item's key features.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "key_infos")
public class KeyInfo {

    /**
     The id of the key information.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_info_id")
    private Long keyInfoId;

    /**
     The item this key information belongs to.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    @NonNull
    @ToString.Exclude
    private Item item;

    /**
     The brief description of the item's key features.
     */
    @Column(name = "description", nullable = false)
    @NonNull
    private String description;


}
