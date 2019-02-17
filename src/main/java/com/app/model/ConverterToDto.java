package com.app.model;

import com.app.model.outcome.CategoryOutcomeDto;
import com.app.model.outcome.SubCategoryOutcomeDto;
import com.app.model.outcome.UserOutcomeDto;
import com.app.model.entity.CategoryEntity;
import com.app.model.entity.SubCategoryEntity;
import com.app.model.entity.UserEntity;
import com.app.utils.DateConverterUtil;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterToDto {

    public UserOutcomeDto convertToUserOutcomeDto(UserEntity userEntity) {
        Long id = userEntity.getId();
        String login = userEntity.getLogin();
        String email = userEntity.getEmail();
        String firstName = userEntity.getFirstName();
        String lastName = userEntity.getLastName();
        Timestamp timestampRegistrationDate = userEntity.getRegistrationDate();
        String registrationDate = DateConverterUtil.convertTimestampDateToString(timestampRegistrationDate);
        return new UserOutcomeDto(id, login, email, firstName, lastName, registrationDate);
    }

    public CategoryOutcomeDto convertToCategoryOutcomeDto(CategoryEntity categoryEntity) {
        Long id = categoryEntity.getId();
        String name = categoryEntity.getName();
        CategoryOutcomeDto categoryOutcomeDto = new CategoryOutcomeDto(id, name);
        List<SubCategoryEntity> subCategories = categoryEntity.getSubCategories();
        if (!subCategories.isEmpty()){
            List<SubCategoryOutcomeDto> subCategoryOutcomeDtos = new ArrayList<>();
            for (SubCategoryEntity subCategory : subCategories) {
                subCategoryOutcomeDtos.add(convertToSubCategoryOutcomeDto(subCategory));
            }
            categoryOutcomeDto.setSubCategories(subCategoryOutcomeDtos);
        }
        return categoryOutcomeDto;
    }

    public SubCategoryOutcomeDto convertToSubCategoryOutcomeDto(SubCategoryEntity subCategoryEntity){
        Long id = subCategoryEntity.getId();
        String name = subCategoryEntity.getName();
        CategoryEntity categoryEntity = subCategoryEntity.getCategoryEntity();
        CategoryOutcomeDto categoryOutcomeDto = convertToCategoryOutcomeDto(categoryEntity);
        return new SubCategoryOutcomeDto(id, name, categoryOutcomeDto);
    }



}
