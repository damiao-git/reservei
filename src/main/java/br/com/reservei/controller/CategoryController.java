package br.com.reservei.controller;

import br.com.reservei.dto.CategoryRecordDto;
import br.com.reservei.entity.Category;
import br.com.reservei.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity <Object> getCategory(@PathVariable(value = "id") Integer id){

        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Nao foi encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Category> postCategory(@RequestBody CategoryRecordDto categoryRecordDto){
        var category = new Category();
        BeanUtils.copyProperties(categoryRecordDto, category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryRepository.save(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity <Object> updateCategory(@PathVariable(value = "id") Integer id, @RequestBody CategoryRecordDto categoryRecordDto){
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Nao foi encotrado");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Category category = categoryOptional.get();
        BeanUtils.copyProperties(categoryRecordDto, category);
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.save(category));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity <Object> deleteCategory(@PathVariable(value = "id") Integer id){
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Nao foi encotrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        Category category = categoryOptional.get();
        categoryRepository.delete(category);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
