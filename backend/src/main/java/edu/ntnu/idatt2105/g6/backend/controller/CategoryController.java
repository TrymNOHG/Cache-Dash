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

import javax.ws.rs.Path;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @PostMapping("/admin/save")
    @Operation(summary = "Create a new category")
    public ResponseEntity<Object> save(@ParameterObject @RequestBody CategoryEditDTO category) {
        logger.info("Attempting to add new category");
        categoryService.addCategory(category);
        logger.info(category.subCategory() +" was added");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/delete")
    @Operation(summary = "Delete a category")
    public ResponseEntity<Object> delete(@ParameterObject @RequestBody CategoryEditDTO category) {
        logger.info("Attempting to delete category");
        //TODO add func
        logger.info(category.subCategory() +" was deleted");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/load/all/{categoryId}")
    @Operation(summary = "Load all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading categories",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDTO.class)) })}
    )
    public ResponseEntity<CategoryDTO> loadAllCategories(@ParameterObject @PathVariable Long categoryId) {
        logger.info("Attempting to load all categories");
        CategoryDTO mainCategory = categoryService.loadSubCategories(categoryId);
        logger.info("All categories were loaded");
        return ResponseEntity.ok(mainCategory);
    }

    @GetMapping("/load/sub/{categoryId}")
    @Operation(summary = "Load level 1 sub categories of a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading 1 level 1 sub categories of a category",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDTO.class)) })}
    )
    public ResponseEntity<List<CategoryDTO>> loadSubCategoriesShall(@ParameterObject @PathVariable Long categoryId) {
        logger.info("Retrieving level 1 sub categories...");
        List<CategoryDTO> subCategories = categoryService.loadSubCategoriesShallow(categoryId);
        logger.info("The sub categories are: " + subCategories);
        return ResponseEntity.ok().body(subCategories);
    }

    @GetMapping("/load/main")
    @Operation(summary = "Load main categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading main categories",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDTO.class)) })}
    )
    public ResponseEntity<List<CategoryDTO>> loadMainCategories() {
        logger.info("Retrieving main categories...");
        List<CategoryDTO> mainCategories = categoryService.loadSubCategoriesShallow(1L);
        logger.info("The main categories are: " + mainCategories);
        return ResponseEntity.ok().body(mainCategories);
    }

    @GetMapping("/load/{categoryId}")
    @Operation(summary = "Load main categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading main categories",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDTO.class)) })}
    )
    public ResponseEntity<CategoryDTO> loadCategory(@ParameterObject @PathVariable Long categoryId) {
        logger.info("Retrieving main categories...");
        CategoryDTO categoryDTO = categoryService.loadCategoryById(categoryId);
        logger.info("The main categories are: " + categoryDTO);
        return ResponseEntity.ok().body(categoryDTO);
    }

}
