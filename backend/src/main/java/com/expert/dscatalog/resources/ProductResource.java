package com.expert.dscatalog.resources;

import com.expert.dscatalog.dto.ProductDto;
import com.expert.dscatalog.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping( "/products" )
public class ProductResource {

    @Autowired
    private ProductService service;

    @GetMapping()
    public ResponseEntity<Page<ProductDto>> findAll( Pageable pageable ){
        Page<ProductDto> products = service.findAll( pageable );
        return ResponseEntity.ok().body( products );
    }

    @GetMapping( value = "/{id}" )
    public ResponseEntity<ProductDto> findById( @PathVariable Long id ){
        ProductDto product = service.findById( id );
        return ResponseEntity.ok().body( product );
    }

    @PostMapping
    public ResponseEntity<ProductDto> insert( @Valid @RequestBody ProductDto dto ){
        dto = service.insert( dto );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path( "/{id}" )
                .buildAndExpand( dto.getId() ).toUri();
        //System.out.println( "resource = " + dto.getCategories() );
        return ResponseEntity.created( uri ).body( dto );
    }

    @PutMapping( value = "/{id}" )
    public ResponseEntity<ProductDto> update( @Valid @PathVariable Long id, @RequestBody ProductDto dto ){
        dto = service.update( id, dto );

        return ResponseEntity.ok().body( dto );
    }

    @DeleteMapping( value = "/{id}" )
    public ResponseEntity<Void> delete( @PathVariable Long id ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
