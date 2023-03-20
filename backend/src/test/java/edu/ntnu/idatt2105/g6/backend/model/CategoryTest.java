package edu.ntnu.idatt2105.g6.backend.model;

import edu.ntnu.idatt2105.g6.backend.BackendApplication;
import edu.ntnu.idatt2105.g6.backend.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
@RequiredArgsConstructor
class CategoryTest {
    CategoryRepository categoryRepository;
    @Test
    void sub_cat_cannot_be_null(){
        Category cat = new Category();

        assertThrows(SQLException.class, () -> {
            categoryRepository.save(cat);
        });

    }
}