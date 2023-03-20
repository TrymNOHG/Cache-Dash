package edu.ntnu.idatt2105.g6.backend.dto.listing;

import lombok.*;

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

    @NonNull
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
}
