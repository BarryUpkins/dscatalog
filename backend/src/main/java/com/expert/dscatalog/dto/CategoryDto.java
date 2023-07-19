package com.expert.dscatalog.dto;

import com.expert.dscatalog.entities.Category;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serial;

public class CategoryDto {
    //@Serial
    private static final long serialVersionUID = 1L;

    //@Id
    //@GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;

    public CategoryDto() { }

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public CategoryDto( Category entity) {
        this.id = entity.getId();
        this.name = "name+"+entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
