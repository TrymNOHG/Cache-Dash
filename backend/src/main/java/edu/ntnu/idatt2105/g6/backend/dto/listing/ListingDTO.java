package edu.ntnu.idatt2105.g6.backend.dto.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.KeyInfo;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ListingDTO {

    @NonNull
    private String username;

    @NonNull
    private String briefDesc;
    private String fullDesc;

    private double latitude;

    @NonNull
    private double longitude;

    @NonNull
    private String county;

    @NonNull
    private String category;

    @NonNull
    private double price;
    private byte[] thumbnail;
    private List<KeyInfo> keyInfoList;
}
