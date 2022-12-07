package com.example.telephonebilling.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "billing")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "call_count")
    private Long callCount;

    @Column(name = "block_count")
    private Long blockCount;
}
