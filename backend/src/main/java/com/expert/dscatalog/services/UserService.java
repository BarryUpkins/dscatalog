package com.expert.dscatalog.services;

import com.expert.dscatalog.dto.UserDto;
import com.expert.dscatalog.entities.User;
import com.expert.dscatalog.repositories.UserRepository;
import com.expert.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> users = repo.findAll();
        //System.out.println( "role 0 = " + users.get(0).getRoles().toArray()[0] + " - " + users.get(0).getRoles().toArray()[1] );
        return users.stream().map(
                e -> new UserDto( e ) ).collect( Collectors.toList() );
    }

    @Transactional(readOnly = true)
    public UserDto findById( Long id ) {
        Optional<User> user = repo.findById( id );
        //user.
        return new UserDto( user.orElseThrow(
                () -> new ResourceNotFoundException("Entity Not Found")));
    }

    @Transactional
    public UserDto insert( UserDto dto ){
        User user = new User();
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setEmail( dto.getEmail() );
//        user.setRoles( dto.getRoles() );
        System.out.println( "user = " + user.getRoles() );
        
        User u = repo.save( user );
        System.out.println( "user = " + dto.getRoles() );

        return new UserDto( u );
    }
}
