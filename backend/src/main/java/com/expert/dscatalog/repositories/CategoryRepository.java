package com.expert.dscatalog.repositories;

import com.expert.dscatalog.dto.CategoryDto;
import com.expert.dscatalog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends
        JpaRepository<Category, Long> {

}