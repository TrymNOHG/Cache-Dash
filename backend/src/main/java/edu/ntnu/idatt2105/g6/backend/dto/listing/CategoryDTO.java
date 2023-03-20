package edu.ntnu.idatt2105.g6.backend.dto.listing;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    @NonNull
    private String subCategory;

    @NonNull
    private String mainCategory;

}
