package br.com.reservei.service;


import br.com.reservei.dto.SubCategoriesRecordDto;
import br.com.reservei.entity.Category;
import br.com.reservei.entity.SubCategories;
import br.com.reservei.repository.CategoryRepository;
import br.com.reservei.repository.SubCategoriesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CategoryRepository categoryRepository;

    public SubCategories saveSubCategory(SubCategoriesRecordDto dto) {
        SubCategories subCategories = new SubCategories();
        subCategories.setName(dto.name());

        // Buscar a categoria pelo ID
        Category category = categoryRepository.findById(dto.category())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
        subCategories.setCategory(category);

        return subCategoriesRepository.save(subCategories);
    }

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
