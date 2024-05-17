package com.example.StudyWithMe.repositories.study.category;

import com.example.StudyWithMe.models.study.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
