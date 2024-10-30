package br.com.reservei.controller;


import br.com.reservei.dto.SubCategoriesRecordDto;
import br.com.reservei.entity.SubCategories;
import br.com.reservei.service.SubCategoriesServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/subcategory")
public class SubCategoryController {

    private final SubCategoriesServices subCategoriesServices;

    @Autowired
    public SubCategoryController(SubCategoriesServices subCategoriesServices){this.subCategoriesServices = subCategoriesServices; }

    @PostMapping
    public ResponseEntity<SubCategories> saveSubCategory(@RequestBody SubCategoriesRecordDto subCategoriesRecordDto) {
        SubCategories savedSubCategory = subCategoriesServices.saveSubCategory(subCategoriesRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubCategory);
    }

    @GetMapping()
    public ResponseEntity<List<SubCategories>> getAllSubCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(subCategoriesServices.getAllSubCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<SubCategories>> GetSubCategoryById(@PathVariable(value = "id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(subCategoriesServices.getSubCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategories> updateSubCategory(@PathVariable(value = "id") Integer id, @RequestBody() SubCategoriesRecordDto subCategoriesRecordDto){
        return ResponseEntity.status(HttpStatus.OK).body(subCategoriesServices.updateSubCategoriy(subCategoriesRecordDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable(value = "id") Integer id){
        subCategoriesServices.deleteSubCategory(id);
        return ResponseEntity.noContent().build();
    }
}
