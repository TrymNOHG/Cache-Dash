package edu.ntnu.idatt2105.g6.backend.controller;

import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingUpdateDTO;
import edu.ntnu.idatt2105.g6.backend.service.listing.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth/listing")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/create")
    @Operation(summary = "List new item")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> create(@ParameterObject @RequestBody ListingDTO listing) {
        itemService.addListing(listing);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    @Operation(summary = "Delete listing")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> delete(@ParameterObject @RequestBody ListingDeletionDTO listing) {
        itemService.deleteListing(listing);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    @Operation(summary = "Update listing")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> update(@ParameterObject @RequestBody ListingUpdateDTO listing) {
        itemService.updateListing(listing);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/load")
    @Operation(summary = "Load all listings")
//    @ExceptionHandler(UserNotFoundException.class)
    //TODO: make this take in token or something
    public ResponseEntity<Object> loadAll(@ParameterObject @RequestBody String username) {
        itemService.loadAllListings(username);
        return ResponseEntity.ok().build();
    }

}
