package com.expert.dscatalog.resources;

import com.expert.dscatalog.dto.UserDto;
import com.expert.dscatalog.entities.User;
import com.expert.dscatalog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value = "/users" )
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        List<UserDto> users = service.findAll();
        return ResponseEntity.ok().body( users );
    }

    @GetMapping( value = "/{id}" )
    public ResponseEntity<UserDto> findById(@PathVariable Long id ){
        UserDto user = service.findById( id );
        return ResponseEntity.ok().body( user );
    }

    @PostMapping
    public ResponseEntity<UserDto> insert( @Valid @RequestBody UserDto dto ){
        UserDto userDto = service.insert( dto );
        return ResponseEntity.ok().body( userDto );
    }
}
