package edu.ntnu.idatt2105.g6.backend.repo.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.listing.ListingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**

 This repository provides CRUD operations for the Item entity.

 It extends JpaRepository and JpaSpecificationExecutor interfaces.

 JpaRepository provides basic CRUD operations while JpaSpecificationExecutor provides
 search functionality using specifications.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {

    /**
     * Finds an item by its item ID.
     *
     * @param itemId the ID of the item to find
     * @return an Optional containing the item with the given ID, or an empty Optional if no such item exists
     */
    Optional<Item> findByItemId(Long itemId);


    Optional<List<Item>> findItemsByCategory_CategoryId(Long categoryId);
    /**
     * Finds a list of items by their main category.
     *
     * @param mainCategory the main category of the items to find
     * @return an Optional containing the list of items with the given main category, or an empty Optional if no such items exist
     */
    Optional<List<Item>> findItemsByCategory_MainCategory(String mainCategory);

    /**
     * Finds a list of items by their sub category.
     *
     * @param subCategory the sub category of the items to find
     * @return an Optional containing the list of items with the given sub category, or an empty Optional if no such items exist
     */
    Optional<List<Item>> findItemsByCategory_SubCategory(String subCategory);

    /**
     * Finds a list of items whose brief description contains the given phrase, ignoring case.
     *
     * @param phrase the phrase to search for
     * @return an Optional containing the list of items whose brief description contains the given phrase, or an empty Optional if no such items exist
     */
    Optional<List<Item>> findItemsByBriefDescContainingIgnoreCase(String phrase);

    /**
     * Finds a list of items located in the given county.
     *
     * @param county the county to search for
     * @return an Optional containing the list of items located in the given county, or an empty Optional if no such items exist
     */
    Optional<List<Item>> findItemsByCounty(String county);

    /**
     * Finds a list of items whose full description contains the given word.
     *
     * @param word the word to search for
     * @return an Optional containing the list of items whose full description contains the given word, or an empty Optional if no such items exist
     */
    Optional<List<Item>> findItemsByFullDescContains(String word);

    /**
     * Finds a list of items belonging to the user with the given username.
     *
     * @param username the username of the user whose items to find
     * @return an Optional containing the list of items belonging to the user with the given username, or an empty Optional if no such items exist
     */
    Optional<List<Item>> findItemsByUser_Username(String username);

    /**
     * Finds a list of items whose price is less than the given price.
     *
     * @param price the maximum price of the items to find
     * @return an Optional containing the list of items whose price is less than the given price, or an empty Optional if no such items exist
     */
    Optional<List<Item>> findItemsByPriceLessThan(double price);

    /**
     * Finds items with prices less than or equal to the given price.
     *
     * @param price The maximum price to search for.
     * @return An optional list of items with prices less than or equal to the given price.
     */
    Optional<List<Item>> findItemsByPriceLessThanEqual(double price);

    /**
     * Finds items with prices greater than the given price.
     *
     * @param price The minimum price to search for.
     * @return An optional list of items with prices greater than the given price.
     */
    Optional<List<Item>> findItemsByPriceGreaterThan(double price);

    /**
     * Finds items with prices greater than or equal to the given price.
     *
     * @param price The minimum price to search for.
     * @return An optional list of items with prices greater than or equal to the given price.
     */
    Optional<List<Item>> findItemsByPriceGreaterThanEqual(double price);

    /**
     * Finds items with prices between the given lower and upper bounds (inclusive).
     *
     * @param lowerBound The lower bound of the price range.
     * @param upperBound The upper bound of the price range.
     * @return An optional list of items with prices between the given lower and upper bounds.
     */
    Optional<List<Item>> findItemsByPriceBetween(double lowerBound, double upperBound);

    /**
     * Finds items by user ID and listing status.
     *
     * @param userId The ID of the user who owns the items.
     * @param status The listing status to search for.
     * @return An optional list of items owned by the user with the given ID and with the given listing status.
     */
    Optional<List<Item>> findItemsByUser_UserIdAndStatus(Long userId ,ListingStatus status);
//    Optional<List<Item>> findItemsByPrice(double lowerBound, double upperBound); //Test what this does

    /**
     * Finds a list of items whose full description contains the given word.
     *
     * @param word the word to search for
     * @return an Optional containing the list of items whose full description contains the given word, or an empty Optional if no such items exist
     */
    Optional<List<Item>> findItemsByFullDescContainsIgnoreCase(String word);






}
