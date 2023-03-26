package edu.ntnu.idatt2105.g6.backend.dto.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.KeyInfo;
import edu.ntnu.idatt2105.g6.backend.model.listing.ListingStatus;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ListingLoadDTO {

    @NonNull
    private Long itemId;

    @NonNull
    private String username;

    @NonNull
    private String briefDesc;
    private String fullDesc;

    @NonNull
    private String address;

    @NonNull
    private String county;

    @NonNull
    private Long categoryId;

    @NonNull
    private double price;
    @NonNull
    private ListingStatus listingStatus;
    @ToString.Exclude
    private byte[] thumbnail;
    private List<KeyInfo> keyInfoList;
}
