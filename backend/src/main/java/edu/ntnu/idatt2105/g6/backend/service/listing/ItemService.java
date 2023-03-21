package edu.ntnu.idatt2105.g6.backend.service.listing;

import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingUpdateDTO;
import edu.ntnu.idatt2105.g6.backend.exception.UnauthorizedException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.CategoryNotFound;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.ItemNotFoundException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.UserNotFoundException;
import edu.ntnu.idatt2105.g6.backend.mapper.listing.ListingMapper;
import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.listing.CategoryRepository;
import edu.ntnu.idatt2105.g6.backend.repo.listing.ItemRepository;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ItemService implements IItemService{

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ListingDTO loadListing(Long itemId) {
        return ListingMapper.toListing(itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException(itemId)));
    }

    @Override
    public List<ListingDTO> loadAllListings(String username) {
        List<Item> listings = itemRepository.findItemsByUser_Username(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return listings.stream()
                .map(ListingMapper::toListing)
                .toList();
    }

    @Override
    public void addListing(ListingDTO listing) {
        User user = userRepository.findByUsername(listing.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(listing.getUsername()));
        Category category = categoryRepository.findBySubCategory(listing.getCategory())
                .orElseThrow(() -> new CategoryNotFound(listing.getCategory()));
        Item item = ListingMapper.toItem(user, category, listing);
        itemRepository.save(item);
    }

    @Override
    public void updateListing(ListingUpdateDTO listingUpdateDTO) {
        Item item = itemRepository.findByItemId(listingUpdateDTO.itemId())
                    .orElseThrow(() -> new ItemNotFoundException(listingUpdateDTO.itemId()));
        item = Item.builder()
                .briefDesc(listingUpdateDTO.briefDesc() != null ? listingUpdateDTO.briefDesc() : item.getBriefDesc())
                .fullDesc(listingUpdateDTO.fullDesc() != null ? listingUpdateDTO.fullDesc() : item.getFullDesc())
                .address(listingUpdateDTO.address() != null ? listingUpdateDTO.address() : item.getAddress())
                .county(listingUpdateDTO.county() != null ? listingUpdateDTO.county() : item.getCounty())
                .category(listingUpdateDTO.category() != null
                        ? categoryRepository.findBySubCategory(listingUpdateDTO.category())
                            .orElseThrow(() -> new CategoryNotFound(listingUpdateDTO.category()))
                        : item.getCategory())
                .price(listingUpdateDTO.price() != null ? listingUpdateDTO.price() : item.getPrice())
                .thumbnail(listingUpdateDTO.thumbnail() != null ? listingUpdateDTO.thumbnail() : item.getThumbnail())
                .keyInfoList(listingUpdateDTO.keyInfoList() != null ? listingUpdateDTO.keyInfoList() : item.getKeyInfoList())
                .build();

        itemRepository.save(item);
    }

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

    public boolean userAuthorized(User user, Item item) {
        return user.getRole() == Role.ADMIN || Objects.equals(item.getUser().getUserId(), user.getUserId());
    }


}
