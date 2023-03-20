package edu.ntnu.idatt2105.g6.backend.dto;

import lombok.*;

@Data
public class BookmarkDTO {
    @NonNull
    private Long itemId;

    @NonNull
    private String username;
}
