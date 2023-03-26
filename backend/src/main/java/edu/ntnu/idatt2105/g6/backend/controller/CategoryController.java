package edu.ntnu.idatt2105.g6.backend.controller;

import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryEditDTO;
import edu.ntnu.idatt2105.g6.backend.service.listing.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

//    @ExceptionHandler(UserNotFoundException.class)
    @PostMapping("/admin/save")
    @Operation(summary = "Create a new category")
    public ResponseEntity<Object> save(@ParameterObject @RequestBody CategoryEditDTO category) {
        logger.info("Attempting to add new category");
        categoryService.addCategory(category);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/delete")
    @Operation(summary = "Delete a category")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> delete(@ParameterObject @RequestBody CategoryEditDTO category) {
        return ResponseEntity.ok().build();
    }

    //TODO: fix DTO for loadBookmarks why UserDeletionDTO
    @GetMapping("/load/all")
    @Operation(summary = "Load all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading categories",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDTO.class)) })}
    )
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CategoryDTO> loadAllCategories(@ParameterObject @RequestParam Long categoryId) {
        CategoryDTO mainCategory = categoryService.loadSubCategories(categoryId);
        return ResponseEntity.ok(mainCategory);
    }

    @GetMapping("/load/main")
    @Operation(summary = "Load main categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading main categories",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDTO.class)) })}
    )
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<List<CategoryDTO>> loadMainCategories() {
        logger.info("Retrieving main categories...");
        List<CategoryDTO> mainCategories = categoryService.loadSubCategoriesShallow(1L);
        logger.info("The main categories are: " + mainCategories);
        return ResponseEntity.ok().body(mainCategories);
    }

}
