package com.herokuapp.mrndesign.matned.model.security;

import com.herokuapp.mrndesign.matned.model.audit.AuditInterface;
import com.herokuapp.mrndesign.matned.model.audit.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserRole  extends BaseEntity implements AuditInterface {



    private String roleName;

    public UserRole() {
    }

    public UserRole(Role roleName){
        this.roleName = roleName.roleName();
    }

    public static UserRole apply(Role roleName){
        return new UserRole(roleName);
    }

    public String getRoleName() {
        return roleName;
    }

    public enum Role {
        ADMIN,
        CEO,
        MANAGER,
        PUBLISHER,
        USER,
        BANNED;

        public String roleName(){
            return "ROLE_" + this.name();
        }
    }
}
