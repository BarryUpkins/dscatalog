package com.expert.dscatalog.services;

import com.expert.dscatalog.entities.Category;
import com.expert.dscatalog.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    @Transactional( readOnly = true )
    public List<Category> findAll(){
        return repo.findAll();
    }
}
