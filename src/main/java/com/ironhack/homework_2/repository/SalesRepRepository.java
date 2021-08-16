package com.ironhack.homework_2.repository;

import com.ironhack.homework_2.dao.salesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepRepository extends JpaRepository<salesRep, Long> {
}
