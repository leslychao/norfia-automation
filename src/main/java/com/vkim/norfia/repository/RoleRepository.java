package com.vkim.norfia.repository;

import com.vkim.norfia.entity.UserRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRolesEntity, Long> {

}
