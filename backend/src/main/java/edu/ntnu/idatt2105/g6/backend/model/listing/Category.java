package edu.ntnu.idatt2105.g6.backend.model.listing;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * This class uses an adjacency list model in order to store the data of categories. Intrinsically, categories follow
 * a tree structure/hierarchical structure, where each node stores a pointer to their parent node.
 *
 * In order to simplify and speed up queries, a nested set model could be implemented instead.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "sub_category")
    @NonNull
    private String subCategory;

    @OneToMany(mappedBy = "mainCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Category> subCategories = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_category_id", referencedColumnName = "category_id", nullable = true)
    @ToString.Exclude
    private Category mainCategory;

}
