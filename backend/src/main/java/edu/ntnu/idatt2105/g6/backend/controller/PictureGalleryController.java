package edu.ntnu.idatt2105.g6.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ntnu.idatt2105.g6.backend.dto.listing.*;
import edu.ntnu.idatt2105.g6.backend.service.listing.CategoryService;
import edu.ntnu.idatt2105.g6.backend.service.listing.ItemService;
import edu.ntnu.idatt2105.g6.backend.service.listing.PictureService;
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
public class PictureGalleryController {

    private final PictureService pictureService;
    private final CategoryService categoryService;

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @GetMapping("/load/pictures/{itemId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Load pictures from item by item's id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListingLoadDTO.class)) })}
    )
    public ResponseEntity<PictureDTO> loadPicturesById(@ParameterObject @PathVariable Long itemId) {
        logger.info("Looking for pictures from item with Id: " + itemId);
        PictureDTO pictureDTOS = pictureService.loadAllImagesByItemId(itemId);
        logger.info("Pictures found!");
        return ResponseEntity.ok(pictureDTOS);
    }

}
