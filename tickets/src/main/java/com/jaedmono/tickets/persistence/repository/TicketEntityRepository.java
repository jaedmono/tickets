package com.jaedmono.tickets.persistence.repository;

import com.jaedmono.tickets.persistence.model.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketEntityRepository extends JpaRepository<TicketEntity, Long> {
}