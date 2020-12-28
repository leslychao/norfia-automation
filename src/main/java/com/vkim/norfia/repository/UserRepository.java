package com.vkim.norfia.repository;

import com.vkim.norfia.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUserName(String userName);

}
