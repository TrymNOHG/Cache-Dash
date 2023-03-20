package edu.ntnu.idatt2105.g6.backend.dto;


import lombok.*;

@Data
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkDTO {
    @NonNull
    private Long itemId;

    @NonNull
    private String username;
}
