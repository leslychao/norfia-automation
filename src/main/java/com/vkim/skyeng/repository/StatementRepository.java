package com.vkim.skyeng.repository;

import com.vkim.skyeng.entity.StatementEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends CrudRepository<StatementEntity, Long> {

  List<StatementEntity> findByPackIdOrderByIdAsc(String packId);
}
