package edu.ntnu.idatt2105.g6.backend.repo.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByItemId(Long itemId);
    Optional<List<Item>> findItemsByCategory_MainCategory(String mainCategory);
    Optional<List<Item>> findItemsByCategory_SubCategory(String subCategory);
    Optional<List<Item>> findItemsByBriefDescContainingIgnoreCase(String phrase);
    Optional<List<Item>> findItemsByCounty(String county);
    Optional<List<Item>> findItemsByFullDescContains(String word);
    Optional<List<Item>> findItemsByUser_Username(String username);
    Optional<List<Item>> findItemsByPriceLessThan(double price);
    Optional<List<Item>> findItemsByPriceLessThanEqual(double price);
    Optional<List<Item>> findItemsByPriceGreaterThan(double price);
    Optional<List<Item>> findItemsByPriceGreaterThanEqual(double price);
    Optional<List<Item>> findItemsByPriceBetween(double lowerBound, double upperBound);
//    Optional<List<Item>> findItemsByPrice(double lowerBound, double upperBound); //Test what this does






}
