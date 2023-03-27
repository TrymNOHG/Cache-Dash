package edu.ntnu.idatt2105.g6.backend.service.listing;

import edu.ntnu.idatt2105.g6.backend.dto.listing.*;
import edu.ntnu.idatt2105.g6.backend.exception.UnauthorizedException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.CategoryNotFound;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.ItemNotFoundException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.NotFoundException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.UserNotFoundException;
import edu.ntnu.idatt2105.g6.backend.mapper.listing.ListingMapper;
import edu.ntnu.idatt2105.g6.backend.mapper.listing.PictureMapper;
import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.listing.ListingStatus;
import edu.ntnu.idatt2105.g6.backend.model.listing.PictureGallery;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.listing.CategoryRepository;
import edu.ntnu.idatt2105.g6.backend.repo.listing.ItemRepository;
import edu.ntnu.idatt2105.g6.backend.repo.listing.PictureGalleryRepository;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import edu.ntnu.idatt2105.g6.backend.specification.ItemSpecifications;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 This service class handles the business logic for item-related operations.
 It implements the IItemService interface.

 */
@Service
@RequiredArgsConstructor
public class ItemService implements IItemService{

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final Logger logger = LoggerFactory.getLogger(ItemService.class);
    private final PictureGalleryRepository pictureGalleryRepository;

    /**
     * Load a single listing based on the provided item ID.
     *
     * @param itemId The ID of the item to load.
     * @return The loaded item as a ListingLoadDTO.
     * @throws ItemNotFoundException if the item with the provided ID does not exist in the database.
     */
    @Override
    public ListingLoadDTO loadListing(Long itemId) {
        return ListingMapper.toListing(itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException(itemId)));
    }

    /**
     * Load all listings associated with the provided username.
     *
     * @param username The username associated with the listings to load.
     * @return A list of all listings associated with the provided username as ListingLoadDTOs.
     * @throws UsernameNotFoundException if the provided username does not exist in the database.
     */
    @Override
    public List<ListingLoadDTO> loadAllListingsByUsername(String username) {
        List<Item> listings = itemRepository.findItemsByUser_Username(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return listings.stream()
                .map(ListingMapper::toListing)
                .toList();
    }

    /**
     * Load all listings.
     *
     * @return A list of all listings as ListingLoadDTOs.
     */
    @Override
    public List<ListingLoadDTO> loadAllListingsByCategoryId(Long categoryId) {
        List<ListingLoadDTO> listings = new ArrayList<>();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFound(categoryId));
        Specification<Item> specification = ItemSpecifications.itemsUnderCategory(category);

        logger.info("Sub categories of " + categoryId + ": " + category.getSubCategory());
        category.getSubCategories()
                .forEach(subCategory -> listings.addAll(loadAllListingsByCategoryId(subCategory.getCategoryId())));
        listings.addAll(itemRepository.findAll(specification)
                .stream().map(ListingMapper::toListing).toList());

        return listings;
    }


    @Override
    public List<ListingLoadDTO> loadAllListings() {
        List<Item> listings = itemRepository.findAll();
        return listings.stream()
                .map(ListingMapper::toListing)
                .toList();
    }


    /**
     * Load all archived listings associated with the provided user ID.
     *
     * @param userId The ID of the user associated with the archived listings to load.
     * @return A list of all archived listings associated with the provided user ID as ListingLoadDTOs.
     * @throws UserNotFoundException if the user with the provided ID does not exist in the database.
     * @throws NotFoundException if no archived listings exist for the user with the provided ID.
     */
    @Override
    public List<ListingLoadDTO> loadArchive(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        List<Item> archivedItems = itemRepository.findItemsByUser_UserIdAndStatus(userId, ListingStatus.ARCHIVED)
                .orElseThrow(() -> new NotFoundException("Item with userId", String.valueOf(userId)));

        return archivedItems.stream().map(ListingMapper::toListing).toList();
    }

    /**
     * Add a new listing to the database.
     *
     * @param listing The listing to add as a ListingDTO.
     * @throws UsernameNotFoundException if the username associated with the listing does not exist in the database.
     * @throws CategoryNotFound if the category associated with the listing does not exist in the database.
     */
    @Transactional
    @Override
    public void addListing(ListingDTO listing) {
        //TODO: check that the username of the listing is the same as the one who sent it: auth token
        logger.info("User creating new listing: " + listing.getUsername());
        User user = userRepository.findByUsername(listing.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(listing.getUsername()));
        Category category = categoryRepository.findById(listing.getCategoryId())
                .orElseThrow(() -> new CategoryNotFound(listing.getCategoryId()));

        logger.info("Category of item: " + category);
        Item item = ListingMapper.toItem(user, category, listing);
        item.setStatus(ListingStatus.ACTIVE);



        Item savedItem = itemRepository.save(item);
        logger.info("The listing has been saved!");

        logger.info("Saving the pictures to gallery...");
        listing.getPictures().forEach(picture -> {
            pictureGalleryRepository.save(PictureMapper.toPictureGallery(picture, savedItem));
        });

    }

    /**
     * Update an existing listing in the database.
     *
     * @param listingUpdateDTO The updated listing information as a ListingUpdateDTO.
     * @throws ItemNotFoundException if the item with the provided ID does not exist in the database.
     * @throws CategoryNotFound if the updated category associated with the listing does not exist in the database.
     */
    @Transactional
    @Override
    public void updateListing(ListingUpdateDTO listingUpdateDTO) {
        User user = userRepository.findByUsername(listingUpdateDTO.username())
                .orElseThrow(() -> new UserNotFoundException(listingUpdateDTO.username()));
        Item item = itemRepository.findByItemId(listingUpdateDTO.itemId())
                    .orElseThrow(() -> new ItemNotFoundException(listingUpdateDTO.itemId()));
        item.setBriefDesc(listingUpdateDTO.briefDesc() != null ? listingUpdateDTO.briefDesc() : item.getBriefDesc());
        item.setFullDesc(listingUpdateDTO.fullDesc() != null ? listingUpdateDTO.fullDesc() : item.getFullDesc());
        item.setAddress(listingUpdateDTO.address() != null ? listingUpdateDTO.address() : item.getAddress());
        item.setCounty(listingUpdateDTO.county() != null ? listingUpdateDTO.county() : item.getCounty());
        item.setCategory(listingUpdateDTO.category() != null
                        ? categoryRepository.findBySubCategory(listingUpdateDTO.category())
                            .orElseThrow(() -> new CategoryNotFound(listingUpdateDTO.category()))
                        : item.getCategory());
        item.setPrice(listingUpdateDTO.price() != null ? listingUpdateDTO.price() : item.getPrice());
        item.setStatus(listingUpdateDTO.listingStatus() != null ? listingUpdateDTO.listingStatus() : item.getStatus());
        item.setThumbnail(listingUpdateDTO.thumbnail() != null ? listingUpdateDTO.thumbnail() : item.getThumbnail());
        item.setKeyInfoList(listingUpdateDTO.keyInfoList() != null ? listingUpdateDTO.keyInfoList() : item.getKeyInfoList());
        item.setUser(user);

        logger.info("New item: " + item.toString());

        itemRepository.save(item);
    }

    /**
     * Change the status of an existing listing in the database when sold.
     *
     * @param listingStatusDTO The updated listing status information as a ListingStatusDTO.
     * @throws ItemNotFoundException if the item with the provided ID does not exist in the database.
     * @throws UserNotFoundException if the user associated with the item does not exist in the database.
     * @throws UnauthorizedException if the user associated with the item is not authorized to change its status.
     */
    @Transactional
    @Override
    public void sellListing(ListingStatusDTO listingStatusDTO) {
        Item item = itemRepository.findById(listingStatusDTO.itemId())
                .orElseThrow(() -> new ItemNotFoundException(listingStatusDTO.itemId()));
        User user = userRepository.findById(listingStatusDTO.itemId())
                .orElseThrow(() -> new UserNotFoundException(listingStatusDTO.itemId()));
        if(!userAuthorized(user, item)) throw new UnauthorizedException(user.getUsername());

        item.setStatus(listingStatusDTO.listingStatus());
        itemRepository.save(item);
    }

    /**
     Deletes a listing from the system.

     @param listingDeletionDTO the DTO containing the ID of the item to be deleted and the username of the user requesting the deletion.
     @throws ItemNotFoundException if the item with the given ID is not found in the system.
     @throws UserNotFoundException if the user with the given username is not found in the system.
     @throws UnauthorizedException if the user does not have authorization to delete the listing.
     */
    @Transactional
    @Override
    public void deleteListing(ListingDeletionDTO listingDeletionDTO) {
        /* Check authorization */
        Item item = itemRepository.findById(listingDeletionDTO.itemId())
                .orElseThrow(() -> new ItemNotFoundException(listingDeletionDTO.itemId()));
        User user = userRepository.findByUsername(listingDeletionDTO.username())
                .orElseThrow(() -> new UserNotFoundException(listingDeletionDTO.username()));
        if(!userAuthorized(user, item)) throw new UnauthorizedException(user.getUsername());

        /* Delete Listing */
        itemRepository.delete(item);

    }

    /**
     * Checks if a user is authorized to perform a certain action on an item.
     *
     * @param user the user to check.
     * @param item the item to check.
     * @return true if the user is authorized to perform the action, false otherwise.
     */
    public boolean userAuthorized(User user, Item item) {
        return user.getRole() == Role.ADMIN || Objects.equals(item.getUser().getUserId(), user.getUserId());
    }


}
