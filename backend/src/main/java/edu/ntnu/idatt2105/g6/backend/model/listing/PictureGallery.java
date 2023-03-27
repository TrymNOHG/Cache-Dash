package edu.ntnu.idatt2105.g6.backend.model.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import jakarta.persistence.*;
import lombok.*;

/**
 This class represents the picture gallery of an item in the e-commerce app. It stores the picture of the item as a byte array.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "picture_gallery")
public class PictureGallery {

    /**
     The id of the picture gallery.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_gallery_id")
    private Long pictureGalleryId;

    /**
     The item to which the picture gallery belongs.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    @NonNull
    @ToString.Exclude
    private Item item;

    /**
     The picture of the item in the picture gallery, stored as a byte array.
     */
    @Column(name = "picture", nullable = false, columnDefinition="longblob")
    @Lob
    private byte @NonNull [] picture;

}
