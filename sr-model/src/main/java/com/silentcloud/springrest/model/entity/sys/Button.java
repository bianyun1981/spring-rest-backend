package com.silentcloud.springrest.model.entity.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 按钮权限
 *
 * @author bianyun
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sys_button")
public class Button extends AbstractPersistable<Long> {

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String value;

    private Integer showOrder;

    @ManyToOne(optional = false)
    private Menu parentMenu;

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "sys_button_api_perm",
            joinColumns = @JoinColumn(name = "button_id"),
            inverseJoinColumns = @JoinColumn(name = "api_perm_id"))
    private Set<ApiPerm> apiPerms = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "buttons")
    private Set<Role> roles = new HashSet<>();

}
