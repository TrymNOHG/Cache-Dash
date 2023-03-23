package edu.ntnu.idatt2105.g6.backend.controller;

import edu.ntnu.idatt2105.g6.backend.dto.listing.*;
import edu.ntnu.idatt2105.g6.backend.service.listing.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/auth/listing")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/create")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> create(@RequestBody ListingDTO listing) {
        itemService.addListing(listing);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> delete(@RequestBody ListingDeletionDTO listing) {
        itemService.deleteListing(listing);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sell")
    public ResponseEntity<Object> sell(@RequestBody ListingStatusDTO listingStatus) {
        itemService.sellListing(listingStatus);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> update(@RequestBody ListingUpdateDTO listing) {
        itemService.updateListing(listing);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/load/all")
//    @ExceptionHandler(UserNotFoundException.class)
    //TODO: make this take in token or something
    public ResponseEntity<Object> loadAll(@RequestBody String username) {
        itemService.loadAllListings(username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/load/archive")
    public ResponseEntity<Object> loadArchive(@RequestBody Long userId) {
        List<ListingLoadDTO> archivedItems = itemService.loadArchive(userId);
        return ResponseEntity.ok(archivedItems);
    }

}
