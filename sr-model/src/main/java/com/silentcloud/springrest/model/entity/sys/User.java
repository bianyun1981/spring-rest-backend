package com.silentcloud.springrest.model.entity.sys;

import cn.hutool.core.util.StrUtil;
import com.silentcloud.springrest.model.entity.AbstractAuditableEntity;
import com.silentcloud.springrest.model.entity.Activatable;
import com.silentcloud.springrest.model.entity.LogicallyDeletable;
import com.silentcloud.springrest.model.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "sys_user")
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractAuditableEntity implements LogicallyDeletable, Activatable {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String realName;

    private String nickName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String mobile;

    private String pictureUrl;

    @Column(nullable = false)
    private Gender gender;

    boolean active;

    boolean deleted;

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public void logicallyDelete() {
        setUsername(generateDeletedValue(getUsername()));
        setDeleted(true);

        if (StrUtil.isNotBlank(getEmail())) {
            setEmail(generateDeletedValue(getEmail()));
        }
        if (StrUtil.isNotBlank(getMobile())) {
            setMobile(generateDeletedValue(getMobile()));
        }
    }

}