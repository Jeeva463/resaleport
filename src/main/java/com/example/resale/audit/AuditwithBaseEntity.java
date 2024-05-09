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
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@EqualsAndHashCode(callSuper =  true)
public class AuditwithBaseEntity extends BaseEntity{
	@CreatedDate
	@Column(name="created_at",nullable = false,updatable = false)
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	@Column(name="update_at",nullable = false,updatable = false)
	private LocalDateTime updateAt;
	
	@CreatedBy
	@Column(name="created_by",nullable = false,updatable = false)
	private String createdBy;
	
	@LastModifiedBy
	@Column(name="update_by",insertable = false)
	private String updateBy;

}
