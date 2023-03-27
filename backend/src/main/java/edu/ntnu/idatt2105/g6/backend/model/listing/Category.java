package edu.ntnu.idatt2105.g6.backend.model.listing;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    /**
     The ID of the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    /**
     The name of the sub-category.
     */
    @Column(name = "sub_category")
    @NonNull
    private String subCategory;

    /**
     The set of sub-categories under this category.
     */
    @OneToMany(mappedBy = "mainCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Set<Category> subCategories = new HashSet<>();

    /**
     The parent category of this category. If this is a root category, this field will be null.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_category_id", referencedColumnName = "category_id", nullable = true)
    @ToString.Exclude
    private Category mainCategory;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<Item> itemsWithCategory = new ArrayList<>();

}
