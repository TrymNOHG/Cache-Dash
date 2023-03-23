package edu.ntnu.idatt2105.g6.backend.controller;

import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryEditDTO;
import edu.ntnu.idatt2105.g6.backend.repo.chat.ConversationRepository;
import edu.ntnu.idatt2105.g6.backend.service.listing.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

//    @ExceptionHandler(UserNotFoundException.class)
    @PostMapping("/save")
    @Operation(summary = "Create a new category")
    public ResponseEntity<Object> save(@ParameterObject @RequestBody CategoryEditDTO category) {
        logger.info("Attempting to add new category");
        categoryService.addCategory(category);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    @Operation(summary = "Delete a category")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> delete(@ParameterObject @RequestBody CategoryEditDTO category) {
        return ResponseEntity.ok().build();
    }

    //TODO: fix DTO for loadBookmarks why UserDeletionDTO
    @PostMapping("/load")
    @Operation(summary = "Load all categories")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> loadAllCategories(@ParameterObject @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok().build();
    }

}
