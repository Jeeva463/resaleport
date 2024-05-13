package com.example.resale.audit;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class Audit {
	 @CreatedDate
	    @Column(name = "created_at",nullable = false, updatable = false)
	    private LocalDateTime createdAt;

	    @LastModifiedDate
	    @Column(name = "updated_at",insertable = false)
	    private LocalDateTime updatedAt;

	    @CreatedBy
	    @Column(name = "created_by",nullable = false,updatable = false)
	    private String createdBy;
	    @LastModifiedBy
	    @Column(name = "updated_by",insertable = false)
	    private String updatedBy;


}
