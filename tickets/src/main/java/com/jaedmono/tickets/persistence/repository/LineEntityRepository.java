package com.jaedmono.tickets.persistence.repository;

import com.jaedmono.tickets.persistence.model.LineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineEntityRepository extends JpaRepository<LineEntity, Long> {
}