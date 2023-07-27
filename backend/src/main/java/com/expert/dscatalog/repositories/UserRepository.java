package com.expert.dscatalog.repositories;

import com.expert.dscatalog.entities.Category;
import com.expert.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail( String email );
}
