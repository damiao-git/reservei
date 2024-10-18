package br.com.reservei.service;


import br.com.reservei.dto.SubCategoriesRecordDto;
import br.com.reservei.entity.SubCategories;
import br.com.reservei.repository.SubCategoriesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoriesServices {

    private final SubCategoriesRepository subCategoriesRepository;

    @Autowired
    public SubCategoriesServices(SubCategoriesRepository subCategoriesRepository){
        this.subCategoriesRepository = subCategoriesRepository;
    }

    public List<SubCategories> getAllSubCategories(){ return subCategoriesRepository.findAll(); }

    public Optional<SubCategories> getSubCategoryById(Integer id){ return subCategoriesRepository.findById(id); }

    public SubCategories saveSubCategory(SubCategories subCategories){ return subCategoriesRepository.save(subCategories); }

    public SubCategories updateSubCategoriy(SubCategoriesRecordDto subCategories, Integer id){
        Optional<SubCategories> item = subCategoriesRepository.findById(id);

        if(item.isEmpty()){
            throw new RuntimeException("User not found with id " + id);
        }

        SubCategories updateItem = item.get();
        BeanUtils.copyProperties(subCategories, updateItem);

        return subCategoriesRepository.save(updateItem);

    }

    public void deleteSubCategory(Integer id){

        if(!subCategoriesRepository.existsById(id)){

            throw new RuntimeException("User not found with id " + id);

        }
        subCategoriesRepository.deleteById(id);

    }
}
