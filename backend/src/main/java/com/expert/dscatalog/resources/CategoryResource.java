package com.expert.dscatalog.resources;

import com.expert.dscatalog.dto.CategoryDto;
import com.expert.dscatalog.entities.Category;
import com.expert.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( value = "/categories" )
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll(){
        List<CategoryDto> list = service.findAll();

        return ResponseEntity.ok().body( list );
    }

    @GetMapping( value = "/{id}" )
    public ResponseEntity<CategoryDto> findById( @PathVariable Long id ){
        CategoryDto category = service.findById( id );

        return ResponseEntity.ok().body( category );
    }
}