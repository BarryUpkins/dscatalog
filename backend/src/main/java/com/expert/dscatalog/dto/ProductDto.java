package com.expert.dscatalog.dto;

import com.expert.dscatalog.entities.Category;
import com.expert.dscatalog.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @Size( min = 5, max = 20, message = "Entre 5 e 400 caracteres" )
    @NotBlank( message = "Campo Obrigatório" )
    private String name;

    @NotBlank( message = "Campo Obrigatório" )
    private String description;

    @Positive( message = "Deve ser Positivo" )
    private Double price;
    private String imgUrl;

    @PastOrPresent( message = "Data não pode ser futura" )
    private Instant date;

    private List<CategoryDto> categories = new ArrayList<>();



    public ProductDto() { }

    public ProductDto( Long id, String name, String description, Double price, String imgUrl, Instant date ){
            //, List<CategoryDto> categories ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
        //this.categories = categories;

    }

    public ProductDto( Product entity ) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
        this.date = entity.getDate();

    }

    public ProductDto( Product entity, Set<Category> categories ) {
        this(entity);
        categories.forEach( cat -> this.categories.add( new CategoryDto(cat) ) );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Instant getDate() {
        return date;
    }


    public void setCategories( List<CategoryDto> collect) {
        collect.forEach( cat -> categories.add( cat ) );
    }

    public List<CategoryDto> getCategories() {
        return categories.stream().toList();
    }
}