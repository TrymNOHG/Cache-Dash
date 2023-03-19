package edu.ntnu.idatt2105.g6.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "sub_category", nullable = false)
    @NonNull
    private String subCategory;

    @Column(name = "main_category", nullable = false)
    @NonNull
    private String mainCategory;

}
