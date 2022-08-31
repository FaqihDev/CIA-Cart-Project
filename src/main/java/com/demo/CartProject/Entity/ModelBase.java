package com.demo.CartProject.Entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@MappedSuperclass
public class ModelBase extends AuditableBase {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column(name = "is_deleted",length = 1)
    private Integer isDeleted;
}
