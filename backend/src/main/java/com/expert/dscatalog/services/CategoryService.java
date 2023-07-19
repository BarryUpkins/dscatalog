package com.expert.dscatalog.services;

import com.expert.dscatalog.dto.CategoryDto;
import com.expert.dscatalog.entities.Category;
import com.expert.dscatalog.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    @Transactional( readOnly = true )
    public List<CategoryDto> findAll(){
        List<Category> list = repo.findAll();
        return list.stream().map(
                e -> new CategoryDto( e ) ).collect( Collectors.toList() );

        /*
        for( Category category : list ){
            listDto.add( new CategoryDto( category ) );
        }
        return listDto;
        */

    }
}
