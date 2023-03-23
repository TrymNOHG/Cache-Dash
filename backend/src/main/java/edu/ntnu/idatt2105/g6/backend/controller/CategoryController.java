package edu.ntnu.idatt2105.g6.backend.controller;

import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.CategoryEditDTO;
import edu.ntnu.idatt2105.g6.backend.repo.chat.ConversationRepository;
import edu.ntnu.idatt2105.g6.backend.service.listing.CategoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/auth/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

//    @ExceptionHandler(UserNotFoundException.class)
    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody CategoryEditDTO category) {
        logger.info("Attempting to add new category");
        categoryService.addCategory(category);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> delete(@RequestBody CategoryEditDTO category) {
        return ResponseEntity.ok().build();
    }

    //TODO: fix DTO for loadBookmarks why UserDeletionDTO
    @PostMapping("/load")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> loadAllCategories(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok().build();
    }

}
