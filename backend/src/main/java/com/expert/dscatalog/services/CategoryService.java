package com.expert.dscatalog.services;

import com.expert.dscatalog.dto.CategoryDto;
import com.expert.dscatalog.entities.Category;
import com.expert.dscatalog.repositories.CategoryRepository;

import com.expert.dscatalog.services.exceptions.DatabaseException;
import com.expert.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    @Transactional(readOnly = true)
    public Page<CategoryDto> findAllPaged(Pageable pageable ) {
        Page<Category> list = repo.findAll( pageable );
        return list.map(e -> new CategoryDto(e));
    }

/*    @Transactional( readOnly = true )
    public List<CategoryDto> findAll(){
        List<Category> list = repo.findAll();
        return list.stream().map(
                e -> new CategoryDto( e ) ).collect( Collectors.toList() );


        //for( Category category : list ){
        //    listDto.add( new CategoryDto( category ) );
        //}
        //return listDto;
    }*/

    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        Optional<Category> opt = repo.findById(id);
        return new CategoryDto(opt.orElseThrow(
                () -> new ResourceNotFoundException("Entity Not Found")));
    }

    @Transactional
    public CategoryDto insert(CategoryDto dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repo.save(entity);
        return new CategoryDto(entity);
    }

    @Transactional
    public CategoryDto update( Long id, CategoryDto dto) {
        try {
            Category entity = repo.getReferenceById(id);
            entity.setName(dto.getName());
            entity = repo.save(entity);

            return new CategoryDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }
    }

}
