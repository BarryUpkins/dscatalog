package com.expert.dscatalog.services;

import com.expert.dscatalog.dto.CategoryDto;
import com.expert.dscatalog.dto.ProductDto;
import com.expert.dscatalog.entities.Category;
import com.expert.dscatalog.entities.Product;
import com.expert.dscatalog.repositories.CategoryRepository;
import com.expert.dscatalog.repositories.ProductRepository;
import com.expert.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private CategoryRepository catRepo;

    @Transactional( readOnly = true )
    public Page<ProductDto> findAll( Pageable pageable ) {
        Page<Product> products = repo.findAll( pageable );
        return products.map( e -> new ProductDto( e ) );
    }

    @Transactional( readOnly = true )
    public ProductDto findById( Long id ) {
        Optional<Product> optional = repo.findById( id );
        Product entity = optional.orElseThrow(
                () -> new ResourceNotFoundException( "Entity Not Found" ) );
        return new ProductDto( entity, entity.getCategories() );
    }

    @Transactional
    public ProductDto insert( ProductDto dto ) {
        Product entity = new Product();
        copyDtoToEntity( entity, dto );
        entity = repo.save( entity );

        return new ProductDto( entity );
    }


    @Transactional
    public ProductDto update( Long id, ProductDto dto ) {
        try {
            Product entity = repo.getReferenceById( id );
            copyDtoToEntity( entity, dto );

            entity = repo.save( entity );

            return new ProductDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException( "Id not found " + id );
        }
    }

    @Transactional
    public void delete( Long id ) {
        repo.deleteById( id );
    }

    private void copyDtoToEntity( Product entity, ProductDto dto ) {
        entity.setName( dto.getName() );
        entity.setDescription( dto.getDescription() );
        entity.setPrice( dto.getPrice() );
        entity.setImgUrl( dto.getImgUrl() );
        entity.setDate( dto.getDate() == null ? Instant.now() : dto.getDate()  );

        entity.getCategories().clear();
        for( CategoryDto catDto : dto.getCategories() ){
            Category category = catRepo.getReferenceById( catDto.getId() );
            entity.getCategories().add( category );
        }
    }

}