package com.ironhack.homework_2.repository;

import com.ironhack.homework_2.dao.Lead;
import com.ironhack.homework_2.dao.salesRep;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeadsRepositoryTest {

    @Autowired
    LeadsRepository leadsRepository;
    @Autowired
    SalesRepRepository salesRepRepository;


    @Test
    void saveANewLead(){
        var LeadCountBeforeSave = leadsRepository.count();
        var howardRep = new salesRep(null, "Howard");
        salesRepRepository.save(howardRep);
        var lead = new Lead(null, "Ben", "123643543", "Ben@BenIndustries.com", "Ben Industries", howardRep);
        leadsRepository.save(lead);
        var LeadCountAfterSave = leadsRepository.count();
        assertEquals(1, LeadCountAfterSave - LeadCountBeforeSave);
    }

    @Test
    void findBySalesRep() {
        var howardRep = new salesRep(null, "Howard");
        salesRepRepository.save(howardRep);
        var lead = new Lead(null, "Ben", "123643543", "Ben@BenIndustries.com", "Ben Industries", howardRep);
        var lead2 = new Lead(null, "Dave", "127456443", "Dave@BenIndustries.com", "Ben Industries", howardRep);
        leadsRepository.save(lead);
        leadsRepository.save(lead2);
        assertEquals(2, leadsRepository.findBySalesRep(howardRep).size());
        System.out.print(howardRep.getName());
        System.out.println(leadsRepository.findBySalesRep(howardRep).size());
    }
}