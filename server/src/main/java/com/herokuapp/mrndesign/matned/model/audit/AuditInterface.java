package com.herokuapp.mrndesign.matned.model.audit;

import com.herokuapp.mrndesign.matned.dto.audit.AuditDTO;
import com.herokuapp.mrndesign.matned.model.Voter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.herokuapp.mrndesign.matned.Patterns.DATE_TIME_FORMATTER;

public interface AuditInterface {

    Long getId();
    Long getVersion();
    Optional<Voter> getCreatedBy();
    Optional<LocalDateTime> getCreatedDate();
    Optional<Voter> getLastModifiedBy();
    Optional<LocalDateTime> getLastModifiedDate();
}

