package edu.ntnu.idatt2105.g6.backend.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreationDTO {

    @NonNull
    private String subCategory;

    @NonNull
    private String mainCategory;

}
