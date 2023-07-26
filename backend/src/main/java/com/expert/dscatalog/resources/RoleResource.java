package com.expert.dscatalog.resources;

import com.expert.dscatalog.dto.RoleDto;
import com.expert.dscatalog.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value = "/roles" )
public class RoleResource {

    @Autowired
    private RoleService service;

    @GetMapping
    public ResponseEntity<List<RoleDto>> findAll(){
        List<RoleDto> roles = service.findAll();
        return ResponseEntity.ok().body( roles );
    }

    @GetMapping( value = "/{id}" )
    public ResponseEntity<RoleDto> findById(@PathVariable Long id ){
        RoleDto role = service.findById( id );
        return ResponseEntity.ok().body( role );
    }

    @PostMapping
    public ResponseEntity<RoleDto> insert( @RequestBody RoleDto dto ){
        RoleDto roleDto = service.insert( dto );
        return ResponseEntity.ok().body( roleDto );
    }
}
