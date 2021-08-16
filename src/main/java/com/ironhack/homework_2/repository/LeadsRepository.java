package com.ironhack.homework_2.repository;

import com.ironhack.homework_2.dao.Lead;
import com.ironhack.homework_2.dao.salesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadsRepository extends JpaRepository<Lead, Long> {
    List<Lead> findBySalesRep(salesRep salesRep);
}
