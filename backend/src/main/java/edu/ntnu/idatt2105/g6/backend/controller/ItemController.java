package edu.ntnu.idatt2105.g6.backend.controller;

import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingUpdateDTO;
import edu.ntnu.idatt2105.g6.backend.service.listing.ItemService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/user/listing")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/create")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> create(@ParameterObject @RequestBody ListingDTO listing) {
        itemService.addListing(listing);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> delete(@ParameterObject @RequestBody ListingDeletionDTO listing) {
        itemService.deleteListing(listing);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> update(@ParameterObject @RequestBody ListingUpdateDTO listing) {
        itemService.updateListing(listing);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/load")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading items",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListingLoadDTO.class)) })}
            )
//    @ExceptionHandler(UserNotFoundException.class)
    //TODO: make this take in token or something
    public ResponseEntity<Object> loadAll(@ParameterObject @RequestBody String username) {
        itemService.loadAllListings(username);
        return ResponseEntity.ok().build();
    }

}
