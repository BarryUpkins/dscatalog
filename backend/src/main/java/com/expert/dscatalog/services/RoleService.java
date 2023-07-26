package com.expert.dscatalog.services;

import com.expert.dscatalog.dto.RoleDto;
import com.expert.dscatalog.entities.Role;
import com.expert.dscatalog.repositories.RoleRepository;
import com.expert.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repo;

    @Transactional(readOnly = true)
    public List<RoleDto> findAll() {
        List<Role> roles = repo.findAll();
        return roles.stream().map(
                e -> new RoleDto( e ) ).collect( Collectors.toList() );
    }

    @Transactional(readOnly = true)
    public RoleDto findById( Long id ) {
        Optional<Role> role = repo.findById( id );
        return new RoleDto( role.orElseThrow(
                () -> new ResourceNotFoundException("Entity Not Found")));
    }

    @Transactional
    public RoleDto insert( RoleDto dto ){
        Role role = new Role();
        role.setId( dto.getId() );
        role.setAuthority( dto.getAuthority() );

        Role u = repo.save( role );
        return new RoleDto( u );
    }
}
