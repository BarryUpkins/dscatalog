package com.expert.dscatalog.resources;

import com.expert.dscatalog.dto.CategoryDto;
import com.expert.dscatalog.entities.Category;
import com.expert.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( value = "/categories" )
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<Page<CategoryDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "moment") String orderBy

    ){
        Pageable pageRequest = PageRequest.of( page, linesPerPage, Sort.Direction.valueOf(direction), orderBy );
        Page<CategoryDto> list = service.findAllPaged( pageRequest );

        return ResponseEntity.ok().body( list );
    }

    @GetMapping( value = "/{id}" )
    public ResponseEntity<CategoryDto> findById( @PathVariable Long id ){
        CategoryDto category = service.findById( id );

        return ResponseEntity.ok().body( category );
    }

    @PostMapping
    public ResponseEntity<CategoryDto> insert( @RequestBody CategoryDto dto ){
        dto = service.insert( dto );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path( "/{id}" )
                .buildAndExpand( dto.getId() ).toUri();
        return ResponseEntity.created( uri ).body( dto );
    }

    @PutMapping( value = "/{id}" )
    public ResponseEntity<CategoryDto> update( @PathVariable Long id, @RequestBody CategoryDto dto ){
        dto = service.update( id, dto );

        return ResponseEntity.ok().body( dto );
    }

    @DeleteMapping( value = "/{id}" )
    public ResponseEntity<Void> update( @PathVariable Long id ){
        service.delete( id );
        return ResponseEntity.noContent().build();
    }
}