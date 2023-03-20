package edu.ntnu.idatt2105.g6.backend.dto;

import edu.ntnu.idatt2105.g6.backend.model.User;
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
    private User user;

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
