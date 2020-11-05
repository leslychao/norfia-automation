package com.vkim.skyeng.repository;

import com.vkim.skyeng.entity.UserRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRolesEntity, Long> {

}
