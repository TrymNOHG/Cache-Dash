package edu.ntnu.idatt2105.g6.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ntnu.idatt2105.g6.backend.dto.listing.*;
import edu.ntnu.idatt2105.g6.backend.service.listing.CategoryService;
import edu.ntnu.idatt2105.g6.backend.service.listing.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/listing")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @PostMapping(value="/user/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Create a listing")
    public ResponseEntity<Object> create(@ParameterObject @RequestPart("listingDTO") String listingDTO,
                                         @ParameterObject @RequestPart("images") List<MultipartFile> images) throws JsonProcessingException {
        logger.info("User wants to create a new listing!");
        ObjectMapper objectMapper = new ObjectMapper();
        ListingDTO listing = objectMapper.readValue(listingDTO, ListingDTO.class);
//        listing.setKeyInfoList(keyInfoList); //TODO: in service convert back to actual keyInfo while saving to db, remember transactional
        List<byte[]> byteImages = images.stream().map(image -> {
            try {
                return image.getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();
        listing.setThumbnail(byteImages.get(0));
        listing.setPictures(byteImages);
        logger.info("A new listing is being created by: " + listing);
        itemService.addListing(listing);
        logger.info("New listing has been added!");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/delete")
    @Operation(summary = "Delete a listing")
    //TODO: make this take in token or something
    public ResponseEntity<Object> delete(@ParameterObject @RequestBody ListingDeletionDTO listing) {
        logger.info(listing.toString() + " is being deleted!");
        itemService.deleteListing(listing);
        logger.info(listing.toString() + " was deleted!");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/update")
    @Operation(summary = "Update a listing")
    public ResponseEntity<Object> update(@ParameterObject @RequestBody ListingUpdateDTO listing) {
        logger.info(listing.toString() + " is being updated!");
        itemService.updateListing(listing);
        logger.info(listing.toString() + " was updated!");
        return ResponseEntity.ok().build();
    }

    //TODO: maybe just add user id to the path?
    @GetMapping("/user/load")
    @Operation(summary = "Load all listings by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading items of a given user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListingLoadDTO.class)) })}
            )
    public ResponseEntity<Object> loadAllByUser(@ParameterObject @RequestParam String username) {
        logger.info("All listings by "+ username + " is being loaded!");
        List<ListingLoadDTO> items = itemService.loadAllListingsByUsername(username);
        logger.info("All listings by "+ username + " were loaded");
        return ResponseEntity.ok(items);
    }

    @GetMapping("/load")
    @Operation(summary = "Load all listings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading all items",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListingLoadDTO.class)) })}
    )
    public ResponseEntity<Object> loadAll() {
        logger.info("All listings are being loaded!");
        itemService.loadAllListings();
        logger.info("All listings were loaded!");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{categoryId}/load")
    @Operation(summary = "Load all listings by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading items of a given user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListingLoadDTO.class)) })}
    )
    public ResponseEntity<List<ListingLoadDTO>> loadAllItemsByCategoryId(@ParameterObject @PathVariable Long categoryId) {
        logger.info("Looking for items under category Id: " + categoryId);
        List<ListingLoadDTO> listings = itemService.loadAllListingsByCategoryId(categoryId);
        logger.info("Items found: " + listings);
        return ResponseEntity.ok(listings);
    }

    @GetMapping("/load/{itemId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Load item by item Id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListingLoadDTO.class)) })}
    )
    public ResponseEntity<ListingLoadDTO> loadItemById(@ParameterObject @PathVariable Long itemId) {
        logger.info("Looking for item with Id: " + itemId);
        ListingLoadDTO listing = itemService.loadListing(itemId);
        logger.info("Item found: " + listing);
        return ResponseEntity.ok(listing);
    }



}
