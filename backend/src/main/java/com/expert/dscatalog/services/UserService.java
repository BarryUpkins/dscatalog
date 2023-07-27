package com.expert.dscatalog.services;

import com.expert.dscatalog.dto.RoleDto;
import com.expert.dscatalog.dto.UserDto;
import com.expert.dscatalog.entities.Role;
import com.expert.dscatalog.entities.User;
import com.expert.dscatalog.repositories.UserRepository;
import com.expert.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder pwEncoder;

    @Autowired
    private UserRepository repo;

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> users = repo.findAll();
        return users.stream().map(
                e -> new UserDto( e ) ).collect( Collectors.toList() );
    }

    @Transactional(readOnly = true)
    public UserDto findById( Long id ) {
        Optional<User> user = repo.findById( id );
        return new UserDto( user.orElseThrow(
                () -> new ResourceNotFoundException("Entity Not Found")));
    }

    @Transactional
    public UserDto insert( UserDto dto ){
        User user = new User();
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setEmail( dto.getEmail() );
        user.setPassword( pwEncoder.encode( dto.getPassword() ));

        user.getRoles().clear();

        dto.getRoles().forEach( role -> user.getRoles().add( new Role( role ) ) );


        User u = repo.save( user );
        return new UserDto( u );
    }
}
