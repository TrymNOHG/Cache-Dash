package edu.ntnu.idatt2105.g6.backend.model.listing;

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
@Table(name = "picture_gallery")
public class PictureGallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_gallery_id")
    private Long pictureGalleryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    @NonNull
    @ToString.Exclude
    private Item item;

    @Column(name = "picture", nullable = false, columnDefinition="longblob")
    @Lob
    private byte @NonNull [] picture;

}
