package com.expert.dscatalog.repositories;

import com.expert.dscatalog.entities.Category;
import com.expert.dscatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends
        JpaRepository<Product, Long> {

}