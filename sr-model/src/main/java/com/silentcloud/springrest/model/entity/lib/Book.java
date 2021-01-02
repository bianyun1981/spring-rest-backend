package com.silentcloud.springrest.model.entity.lib;

import com.silentcloud.springrest.model.entity.AbstractAuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lib_book")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Book extends AbstractAuditableEntity {

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    private Long wordCount;

//    private MonetaryAmount unitPrice;

    private Long unitPrice;

    @ManyToOne(optional = false)
    private Publisher publisher;

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "lib_book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "lib_book_translater",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "translater_id"))
    private List<Translater> translaters;

}
