package edu.ntnu.idatt2105.g6.backend.specification;

import edu.ntnu.idatt2105.g6.backend.metamodel.listing.Category_;
import edu.ntnu.idatt2105.g6.backend.metamodel.listing.Item_;
import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class ItemSpecifications {

    public static Specification<Item> itemsUnderCategory(Category category) {
        return (root, query, criteriaBuilder) -> {
            Join<Item, Category> itemCategoryJoin = root.join(Item_.CATEGORY, JoinType.INNER);
            return criteriaBuilder.equal(itemCategoryJoin.get(Category_.CATEGORYID), category.getCategoryId());
        };
    }

}
