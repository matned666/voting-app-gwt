package com.herokuapp.mrndesign.matned.repository;

import com.herokuapp.mrndesign.matned.model.audit.AuditHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditHistoryRepository extends JpaRepository<AuditHistory, Long> {
}
