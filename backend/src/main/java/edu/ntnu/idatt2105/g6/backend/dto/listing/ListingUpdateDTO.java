package edu.ntnu.idatt2105.g6.backend.dto.listing;


import edu.ntnu.idatt2105.g6.backend.model.listing.KeyInfo;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public record ListingUpdateDTO(@NonNull String username,
                               @NonNull Long itemId,
                               @Nullable String briefDesc,
                               @Nullable String fullDesc,
                               @Nullable String address,
                               @Nullable String county,
                               @Nullable String category,
                               @Nullable Double price,
                               @Nullable byte[] thumbnail,
                               @Nullable List<KeyInfo> keyInfoList
                            ) {
}
