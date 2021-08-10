package org.cannedcoffee.springboot.domain;

// this class will be the super-class to all Entities, where controls date

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
//all fields mapped here are inherited as columns to subclasses
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    //creation date is stored into Entity
    @CreatedDate
    private LocalDateTime createdDate;

    //update date is stored
    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
