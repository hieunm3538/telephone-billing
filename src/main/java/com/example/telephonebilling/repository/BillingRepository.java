package com.example.telephonebilling.repository;

import com.example.telephonebilling.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long> {
    Billing findByUserId(Long id);
}
