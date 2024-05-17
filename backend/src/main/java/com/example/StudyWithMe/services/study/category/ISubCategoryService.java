package com.example.StudyWithMe.services.study.category;
import com.example.StudyWithMe.models.study.category.SubCategory;
import java.util.List;
public interface ISubCategoryService {
    SubCategory createSubCategory();
    List<SubCategory> getAllSubCategory(Long categoryId);
}
