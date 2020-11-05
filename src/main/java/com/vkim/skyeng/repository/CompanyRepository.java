package com.vkim.skyeng.repository;

import com.vkim.skyeng.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

  CompanyEntity findByCompanyNameOrderByIdAsc(String companyName);
}
