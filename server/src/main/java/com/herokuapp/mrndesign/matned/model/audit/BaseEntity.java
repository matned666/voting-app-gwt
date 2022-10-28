package com.herokuapp.mrndesign.matned.model.audit;

import com.herokuapp.mrndesign.matned.model.security.User;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity extends AbstractAuditable<User, Long> {

    @Version
    private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
