package com.example.telephonebillingapplication.repository;

import com.example.telephonebillingapplication.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long> {
    Billing findByUserId(Long id);
}
