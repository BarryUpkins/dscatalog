package com.expert.dscatalog.repositories;

import com.expert.dscatalog.entities.Role;
import com.expert.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
